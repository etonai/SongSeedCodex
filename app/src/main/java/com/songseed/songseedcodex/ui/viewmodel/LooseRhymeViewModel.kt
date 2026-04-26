package com.songseed.songseedcodex.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.songseed.songseedcodex.data.LooseRhymeRepository
import com.songseed.songseedcodex.data.SettingsRepository
import com.songseed.songseedcodex.domain.LooseRhymeDrillSource
import com.songseed.songseedcodex.domain.SettingsState
import com.songseed.songseedcodex.domain.SlantRhymePair
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LooseRhymeUiState(
    val source: LooseRhymeDrillSource,
    val currentPair: SlantRhymePair? = null,
    val isExampleVisible: Boolean = false,
    val settings: SettingsState = SettingsState()
)

class LooseRhymeViewModel(
    private val source: LooseRhymeDrillSource,
    private val repository: LooseRhymeRepository,
    settingsRepository: SettingsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LooseRhymeUiState(source = source))
    private val recentSeeds = ArrayDeque<String>()

    val uiState: StateFlow<LooseRhymeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            settingsRepository.settings.collect { settings ->
                _uiState.value = _uiState.value.copy(settings = settings)
            }
        }
        generateNextPair()
    }

    fun generateNextPair() {
        val settings = uiState.value.settings
        val pair = repository.nextPair(
            source = source,
            recentSeeds = recentSeeds.toList(),
            avoidRecentRepeats = settings.avoidRecentRepeats
        )
        recentSeeds.addFirst(pair.seed)
        while (recentSeeds.size > 8) {
            recentSeeds.removeLast()
        }
        _uiState.value = _uiState.value.copy(
            currentPair = pair,
            isExampleVisible = false
        )
    }

    fun showExample() {
        _uiState.value = _uiState.value.copy(isExampleVisible = true)
    }

    companion object {
        fun factory(
            source: LooseRhymeDrillSource,
            repository: LooseRhymeRepository,
            settingsRepository: SettingsRepository
        ): ViewModelProvider.Factory = SimpleViewModelFactory {
            LooseRhymeViewModel(source, repository, settingsRepository)
        }
    }
}
