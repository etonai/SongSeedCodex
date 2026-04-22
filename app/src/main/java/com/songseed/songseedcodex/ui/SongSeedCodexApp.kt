package com.songseed.songseedcodex.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.songseed.songseedcodex.data.ImprovRepository
import com.songseed.songseedcodex.data.RhymeRepository
import com.songseed.songseedcodex.data.SettingsRepository
import com.songseed.songseedcodex.domain.ImprovMode
import com.songseed.songseedcodex.ui.navigation.Routes
import com.songseed.songseedcodex.ui.screens.HomeScreen
import com.songseed.songseedcodex.ui.screens.ImprovModeSelectionScreen
import com.songseed.songseedcodex.ui.screens.ImprovScreen
import com.songseed.songseedcodex.ui.screens.RhymeScreen
import com.songseed.songseedcodex.ui.screens.SettingsScreen
import com.songseed.songseedcodex.ui.viewmodel.ImprovViewModel
import com.songseed.songseedcodex.ui.viewmodel.RhymeViewModel
import com.songseed.songseedcodex.ui.viewmodel.SettingsViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SongSeedCodexApp(
    improvRepository: ImprovRepository,
    rhymeRepository: RhymeRepository,
    settingsRepository: SettingsRepository
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable(Routes.Home) {
            HomeScreen(
                onImprovClick = { navController.navigate(Routes.ImprovModes) },
                onRhymeClick = { navController.navigate(Routes.Rhyme) },
                onSettingsClick = { navController.navigate(Routes.Settings) }
            )
        }

        composable(Routes.ImprovModes) {
            ImprovModeSelectionScreen(
                onBack = { navController.popBackStack() },
                onModeClick = { mode -> navController.navigate(Routes.improv(mode.name)) }
            )
        }

        composable(
            route = Routes.Improv,
            arguments = listOf(navArgument("mode") { type = NavType.StringType })
        ) { backStackEntry ->
            val mode = ImprovMode.fromRouteValue(backStackEntry.arguments?.getString("mode"))
            val viewModel: ImprovViewModel = viewModel(
                factory = ImprovViewModel.factory(mode, improvRepository, settingsRepository)
            )
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            ImprovScreen(
                state = state,
                onBack = { navController.popBackStack() },
                onGenerate = viewModel::generate
            )
        }

        composable(Routes.Rhyme) {
            val viewModel: RhymeViewModel = viewModel(
                factory = RhymeViewModel.factory(rhymeRepository, settingsRepository)
            )
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            RhymeScreen(
                state = state,
                onBack = { navController.popBackStack() },
                onDifficultyChange = viewModel::setDifficulty,
                onNextWord = viewModel::generateNextWord
            )
        }

        composable(Routes.Settings) {
            val viewModel: SettingsViewModel = viewModel(
                factory = SettingsViewModel.factory(settingsRepository)
            )
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            SettingsScreen(
                state = state,
                onBack = { navController.popBackStack() },
                onAvoidRepeatsChange = viewModel::setAvoidRecentRepeats,
                onTimerSecondsChange = viewModel::setTimerSeconds,
                onCategoryToggle = viewModel::setCategoryEnabled
            )
        }
    }
}
