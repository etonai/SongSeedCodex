package com.songseed.songseedcodex.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.songseed.songseedcodex.BuildConfig
import com.songseed.songseedcodex.domain.ImprovCategory
import com.songseed.songseedcodex.domain.SettingsState

@Composable
fun SettingsScreen(
    state: SettingsState,
    onBack: () -> Unit,
    onAvoidRepeatsChange: (Boolean) -> Unit,
    onTimerSecondsChange: (Int) -> Unit,
    onCategoryToggle: (ImprovCategory, Boolean) -> Unit
) {
    SongSeedCodexScaffold(title = "Settings") { paddingValues ->
        ScrollColumn(paddingValues = paddingValues) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Avoid immediate repeats")
                    Switch(
                        checked = state.avoidRecentRepeats,
                        onCheckedChange = onAvoidRepeatsChange
                    )
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Rhyme timer: ${state.rhymeTimerSeconds}s")
                    Slider(
                        value = state.rhymeTimerSeconds.toFloat(),
                        onValueChange = { onTimerSecondsChange(it.toInt()) },
                        valueRange = 15f..300f
                    )
                }
            }

            Text("Enabled improv categories")

            for (category in ImprovCategory.values()) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(category.displayName)
                        Switch(
                            checked = category in state.enabledCategories,
                            onCheckedChange = { enabled: Boolean -> onCategoryToggle(category, enabled) }
                        )
                    }
                }
            }

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Back")
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "SongSeedCodex",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Version ${BuildConfig.VERSION_NAME}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Developed by Edward T. Tonai",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
