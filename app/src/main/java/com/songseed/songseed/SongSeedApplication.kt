package com.songseed.songseed

import android.app.Application
import com.songseed.songseed.data.ImprovRepository
import com.songseed.songseed.data.RhymeRepository
import com.songseed.songseed.data.SettingsRepository

class SongSeedApplication : Application() {
    lateinit var settingsRepository: SettingsRepository
        private set

    lateinit var improvRepository: ImprovRepository
        private set

    lateinit var rhymeRepository: RhymeRepository
        private set

    override fun onCreate() {
        super.onCreate()
        settingsRepository = SettingsRepository(this)
        improvRepository = ImprovRepository()
        rhymeRepository = RhymeRepository()
    }
}
