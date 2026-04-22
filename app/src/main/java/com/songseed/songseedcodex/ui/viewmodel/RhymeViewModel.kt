package com.songseed.songseedcodex.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.songseed.songseedcodex.data.RhymeRepository
import com.songseed.songseedcodex.data.SettingsRepository
import com.songseed.songseedcodex.domain.RhymeDifficulty
import com.songseed.songseedcodex.domain.SettingsState
import com.songseed.songseedcodex.domain.Word
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

data class RhymeUiState(
    val isEasySelected: Boolean = true,
    val isNormalSelected: Boolean = false,
    val currentWord: Word? = null,
    val settings: SettingsState = SettingsState()
)

class RhymeViewModel(
    private val repository: RhymeRepository,
    settingsRepository: SettingsRepository
) : ViewModel() {
    private val selectedDifficulty = MutableStateFlow(RhymeDifficulty.Easy)
    private val _uiState = MutableStateFlow(RhymeUiState())
    private val recentWords = ArrayDeque<String>()

    val uiState: StateFlow<RhymeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            settingsRepository.settings.collect { settings ->
                _uiState.value = _uiState.value.copy(settings = settings)
            }
        }
        generateNextWord()
    }

    fun setDifficulty(nextDifficulty: RhymeDifficulty) {
        selectedDifficulty.value = nextDifficulty
        _uiState.value = _uiState.value.copy(
            isEasySelected = nextDifficulty == RhymeDifficulty.Easy,
            isNormalSelected = nextDifficulty == RhymeDifficulty.Normal
        )
        generateNextWord()
    }

    fun generateNextWord() {
        val settings = uiState.value.settings
        val word = repository.nextWord(
            difficulty = selectedDifficulty.value,
            recentWords = recentWords.toList(),
            avoidRecentRepeats = settings.avoidRecentRepeats
        )
        recentWords.addFirst(word.text)
        while (recentWords.size > 8) {
            recentWords.removeLast()
        }
        _uiState.value = _uiState.value.copy(
            isEasySelected = selectedDifficulty.value == RhymeDifficulty.Easy,
            isNormalSelected = selectedDifficulty.value == RhymeDifficulty.Normal,
            currentWord = word
        )
    }

    companion object {
        fun factory(
            repository: RhymeRepository,
            settingsRepository: SettingsRepository
        ): ViewModelProvider.Factory = SimpleViewModelFactory {
            RhymeViewModel(repository, settingsRepository)
        }
    }
}
