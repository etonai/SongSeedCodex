package com.songseed.songseedcodex.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.songseed.songseedcodex.domain.ImprovCategory
import com.songseed.songseedcodex.domain.SettingsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "songseedcodex_settings")

class SettingsRepository(private val context: Context) {
    private object Keys {
        val AvoidRecentRepeats = booleanPreferencesKey("avoid_recent_repeats")
        val RhymeTimerSeconds = intPreferencesKey("rhyme_timer_seconds")
        val EnabledCategories = stringSetPreferencesKey("enabled_categories")
    }

    val settings: Flow<SettingsState> = context.dataStore.data.map { preferences ->
        SettingsState(
            avoidRecentRepeats = preferences[Keys.AvoidRecentRepeats] ?: true,
            rhymeTimerSeconds = preferences[Keys.RhymeTimerSeconds] ?: 60,
            enabledCategories = preferences.toEnabledCategories()
        )
    }

    suspend fun setAvoidRecentRepeats(enabled: Boolean) {
        context.dataStore.edit { it[Keys.AvoidRecentRepeats] = enabled }
    }

    suspend fun setRhymeTimerSeconds(seconds: Int) {
        context.dataStore.edit { it[Keys.RhymeTimerSeconds] = seconds.coerceIn(15, 300) }
    }

    suspend fun setCategoryEnabled(category: ImprovCategory, enabled: Boolean) {
        context.dataStore.edit { preferences ->
            val current = preferences[Keys.EnabledCategories]?.toMutableSet()
                ?: ImprovCategory.values().map { it.name }.toMutableSet()
            if (enabled) {
                current += category.name
            } else if (current.size > 1) {
                current -= category.name
            }
            preferences[Keys.EnabledCategories] = current
        }
    }

    private fun Preferences.toEnabledCategories(): Set<ImprovCategory> {
        val saved = this[Keys.EnabledCategories]
        if (saved.isNullOrEmpty()) {
            return ImprovCategory.values().toSet()
        }
        return ImprovCategory.values().filterTo(mutableSetOf()) { it.name in saved }
            .ifEmpty { ImprovCategory.values().toSet() }
    }
}
