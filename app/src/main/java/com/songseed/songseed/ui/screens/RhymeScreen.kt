package com.songseed.songseed.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.songseed.songseed.domain.RhymeDifficulty
import com.songseed.songseed.ui.viewmodel.RhymeUiState

@Composable
fun RhymeScreen(
    state: RhymeUiState,
    onBack: () -> Unit,
    onDifficultyChange: (RhymeDifficulty) -> Unit,
    onNextWord: () -> Unit
) {
    SongSeedScaffold(title = "Rhyme Drill") { paddingValues ->
        ScrollColumn(paddingValues = paddingValues) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FilterChip(
                    selected = state.isEasySelected,
                    onClick = { onDifficultyChange(RhymeDifficulty.Easy) },
                    label = { Text(RhymeDifficulty.Easy.label) }
                )
                FilterChip(
                    selected = state.isNormalSelected,
                    onClick = { onDifficultyChange(RhymeDifficulty.Normal) },
                    label = { Text(RhymeDifficulty.Normal.label) }
                )
            }

            HeroCard(
                eyebrow = "Current word",
                text = state.currentWord?.text ?: "Loading..."
            )

            Button(
                onClick = onNextWord,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Next Word")
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
