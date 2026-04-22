package com.songseed.songseed.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.songseed.songseed.data.ImprovRepository
import com.songseed.songseed.data.RhymeRepository
import com.songseed.songseed.data.SettingsRepository
import com.songseed.songseed.domain.ImprovMode
import com.songseed.songseed.ui.navigation.Routes
import com.songseed.songseed.ui.screens.HomeScreen
import com.songseed.songseed.ui.screens.ImprovModeSelectionScreen
import com.songseed.songseed.ui.screens.ImprovScreen
import com.songseed.songseed.ui.screens.RhymeScreen
import com.songseed.songseed.ui.screens.SettingsScreen
import com.songseed.songseed.ui.viewmodel.ImprovViewModel
import com.songseed.songseed.ui.viewmodel.RhymeViewModel
import com.songseed.songseed.ui.viewmodel.SettingsViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SongSeedApp(
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
