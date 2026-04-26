package com.songseed.songseedcodex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.songseed.songseedcodex.ui.SongSeedCodexApp
import com.songseed.songseedcodex.ui.theme.SongSeedCodexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = application as SongSeedCodexApplication

        setContent {
            SongSeedCodexTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SongSeedCodexApp(
                        improvRepository = app.improvRepository,
                        rhymeRepository = app.rhymeRepository,
                        slantRhymeRepository = app.slantRhymeRepository,
                        looseRhymeRepository = app.looseRhymeRepository,
                        settingsRepository = app.settingsRepository
                    )
                }
            }
        }
    }
}
