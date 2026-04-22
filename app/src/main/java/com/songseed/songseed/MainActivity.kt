package com.songseed.songseed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.songseed.songseed.ui.SongSeedApp
import com.songseed.songseed.ui.theme.SongSeedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = application as SongSeedApplication

        setContent {
            SongSeedTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SongSeedApp(
                        improvRepository = app.improvRepository,
                        rhymeRepository = app.rhymeRepository,
                        settingsRepository = app.settingsRepository
                    )
                }
            }
        }
    }
}
