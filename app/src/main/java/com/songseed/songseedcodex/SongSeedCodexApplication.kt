package com.songseed.songseedcodex

import android.app.Application
import com.songseed.songseedcodex.data.ImprovRepository
import com.songseed.songseedcodex.data.RhymeRepository
import com.songseed.songseedcodex.data.SettingsRepository

class SongSeedCodexApplication : Application() {
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
