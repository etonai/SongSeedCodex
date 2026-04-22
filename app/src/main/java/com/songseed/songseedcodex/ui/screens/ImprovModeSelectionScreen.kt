package com.songseed.songseedcodex.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.songseed.songseedcodex.domain.ImprovMode

@Composable
fun ImprovModeSelectionScreen(
    onBack: () -> Unit,
    onModeClick: (ImprovMode) -> Unit
) {
    SongSeedCodexScaffold(title = "Improv Song") { paddingValues ->
        ScrollColumn(paddingValues = paddingValues) {
            HeroCard(
                eyebrow = "Choose your challenge",
                text = "Normal uses one idea. Difficult combines two or more."
            )

            Button(
                onClick = { onModeClick(ImprovMode.Normal) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Normal Mode")
            }

            Button(
                onClick = { onModeClick(ImprovMode.Difficult) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Difficult Mode")
            }

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Back")
            }
        }
    }
}
