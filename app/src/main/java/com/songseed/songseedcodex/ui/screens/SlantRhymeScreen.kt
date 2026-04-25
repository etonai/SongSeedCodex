package com.songseed.songseedcodex.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.songseed.songseedcodex.ui.viewmodel.SlantRhymeUiState

@Composable
fun SlantRhymeScreen(
    state: SlantRhymeUiState,
    onBack: () -> Unit,
    onNextWord: () -> Unit,
    onShowExample: () -> Unit
) {
    val currentPair = state.currentPair

    SongSeedCodexScaffold(title = "Slant Rhyme Drill") { paddingValues ->
        ScrollColumn(paddingValues = paddingValues) {
            HeroCard(
                eyebrow = "Current word",
                text = currentPair?.seed ?: "Loading..."
            )

            if (state.isExampleVisible) {
                HeroCard(
                    eyebrow = "Example",
                    text = currentPair?.example ?: "Loading..."
                )
            }

            Button(
                onClick = onNextWord,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Next Word")
            }

            OutlinedButton(
                onClick = onShowExample,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Show Example")
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
