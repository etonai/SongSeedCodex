package com.songseed.songseedcodex.ui.screens

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
import com.songseed.songseedcodex.ui.viewmodel.LooseRhymeUiState

@Composable
fun LooseRhymesSelectionScreen(
    onBack: () -> Unit,
    onDefaultClick: () -> Unit,
    onAllClick: () -> Unit,
    onHardEndingShiftClick: () -> Unit
) {
    SongSeedCodexScaffold(title = "Loose Rhymes") { paddingValues ->
        ScrollColumn(paddingValues = paddingValues) {
            HeroCard(
                eyebrow = "Loose Rhymes",
                text = "Find the almost-match."
            )

            Text(
                text = "Subcategories",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Button(
                onClick = onDefaultClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Default")
            }

            Button(
                onClick = onAllClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("All")
            }

            Button(
                onClick = onHardEndingShiftClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Hard Ending Shift")
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

@Composable
fun HardEndingShiftDescriptionScreen(
    onBack: () -> Unit,
    onStartDrill: () -> Unit
) {
    SongSeedCodexScaffold(title = "Hard Ending Shift") { paddingValues ->
        ScrollColumn(paddingValues = paddingValues) {
            HeroCard(
                eyebrow = "Loose Rhyme Type",
                text = "Hard Ending Shift"
            )

            Text(
                text = "These rhymes keep a similar vowel sound, but change the ending so the rhyme feels related without sounding exact.",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Avoid words that are too close, like cod -> cot, and avoid perfect rhymes, like find -> kind.",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Good loose rhymes feel like they belong together, but still surprise you.",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Examples",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "cod -> pot\nbad -> cat\nbed -> step",
                style = MaterialTheme.typography.bodyLarge
            )

            Button(
                onClick = onStartDrill,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Start Drill")
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

@Composable
fun LooseRhymeDrillScreen(
    state: LooseRhymeUiState,
    onNextWord: () -> Unit,
    onShowExample: () -> Unit,
    onMainPage: () -> Unit
) {
    val currentPair = state.currentPair

    SongSeedCodexScaffold(title = state.source.label) { paddingValues ->
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

            OutlinedButton(
                onClick = onShowExample,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Show Example")
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
                onClick = onMainPage,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Main Page")
            }
        }
    }
}
