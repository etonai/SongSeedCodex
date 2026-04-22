package com.songseed.songseed.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.songseed.songseed.ui.viewmodel.ImprovUiState

@Composable
fun ImprovScreen(
    state: ImprovUiState,
    onBack: () -> Unit,
    onGenerate: () -> Unit
) {
    SongSeedScaffold(title = "Improv Song") { paddingValues ->
        ScrollColumn(paddingValues = paddingValues) {
            FilterChip(
                selected = true,
                onClick = {},
                label = { Text("Mode: ${state.mode.label}") }
            )

            HeroCard(
                eyebrow = "Prompt",
                text = state.prompt?.text ?: "Loading..."
            )

            Button(
                onClick = onGenerate,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Generate")
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
