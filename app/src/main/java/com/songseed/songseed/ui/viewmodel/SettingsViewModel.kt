package com.songseed.songseed.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.songseed.songseed.data.SettingsRepository
import com.songseed.songseed.domain.ImprovCategory
import com.songseed.songseed.domain.SettingsState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: SettingsRepository
) : ViewModel() {
    val uiState: StateFlow<SettingsState> = repository.settings.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SettingsState()
    )

    fun setAvoidRecentRepeats(enabled: Boolean) {
        viewModelScope.launch {
            repository.setAvoidRecentRepeats(enabled)
        }
    }

    fun setTimerSeconds(seconds: Int) {
        viewModelScope.launch {
            repository.setRhymeTimerSeconds(seconds)
        }
    }

    fun setCategoryEnabled(category: ImprovCategory, enabled: Boolean) {
        viewModelScope.launch {
            repository.setCategoryEnabled(category, enabled)
        }
    }

    companion object {
        fun factory(repository: SettingsRepository): ViewModelProvider.Factory =
            SimpleViewModelFactory { SettingsViewModel(repository) }
    }
}
