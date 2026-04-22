package com.songseed.songseed.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.songseed.songseed.data.ImprovRepository
import com.songseed.songseed.data.SettingsRepository
import com.songseed.songseed.domain.ImprovMode
import com.songseed.songseed.domain.ImprovPrompt
import com.songseed.songseed.domain.SettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ImprovUiState(
    val mode: ImprovMode,
    val prompt: ImprovPrompt? = null,
    val settings: SettingsState = SettingsState()
)

class ImprovViewModel(
    private val mode: ImprovMode,
    private val repository: ImprovRepository,
    settingsRepository: SettingsRepository
) : ViewModel() {
    private val currentPrompt = MutableStateFlow<ImprovPrompt?>(null)
    private val recentPrompts = ArrayDeque<String>()

    val uiState: StateFlow<ImprovUiState> = combine(
        settingsRepository.settings,
        currentPrompt
    ) { settings, prompt ->
        ImprovUiState(
            mode = mode,
            prompt = prompt,
            settings = settings
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ImprovUiState(mode = mode)
    )

    init {
        generate()
    }

    fun generate() {
        val settings = uiState.value.settings
        val prompt = repository.generatePrompt(
            mode = mode,
            enabledCategories = settings.enabledCategories,
            recentPrompts = recentPrompts.toList(),
            avoidRecentRepeats = settings.avoidRecentRepeats
        )
        rememberPrompt(prompt.text)
        currentPrompt.value = prompt
    }

    private fun rememberPrompt(prompt: String) {
        recentPrompts.addFirst(prompt)
        while (recentPrompts.size > 8) {
            recentPrompts.removeLast()
        }
    }

    companion object {
        fun factory(
            mode: ImprovMode,
            repository: ImprovRepository,
            settingsRepository: SettingsRepository
        ): ViewModelProvider.Factory = SimpleViewModelFactory {
            ImprovViewModel(mode, repository, settingsRepository)
        }
    }
}
