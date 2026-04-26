package com.songseed.songseedcodex.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onImprovClick: () -> Unit,
    onRhymeClick: () -> Unit,
    onLooseRhymesClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    SongSeedCodexScaffold(title = "SongSeedCodex") { paddingValues ->
        ScrollColumn(paddingValues = paddingValues) {
            HeroCard(
                eyebrow = "Fast creative practice",
                text = "Tap, sing, repeat."
            )

            Text(
                text = "Practice Modes",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Button(
                onClick = onImprovClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Improv Song")
            }

            Button(
                onClick = onRhymeClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Rhyme Drill")
            }

            Button(
                onClick = onLooseRhymesClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Loose Rhymes")
            }

            OutlinedButton(
                onClick = onSettingsClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Settings")
            }
        }
    }
}
