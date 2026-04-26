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
import com.songseed.songseedcodex.data.LooseRhymeRepository
import com.songseed.songseedcodex.data.RhymeRepository
import com.songseed.songseedcodex.data.SettingsRepository
import com.songseed.songseedcodex.data.SlantRhymeRepository
import com.songseed.songseedcodex.domain.ImprovMode
import com.songseed.songseedcodex.domain.LooseRhymeDrillSource
import com.songseed.songseedcodex.ui.navigation.Routes
import com.songseed.songseedcodex.ui.screens.HardEndingShiftDescriptionScreen
import com.songseed.songseedcodex.ui.screens.HomeScreen
import com.songseed.songseedcodex.ui.screens.ImprovModeSelectionScreen
import com.songseed.songseedcodex.ui.screens.ImprovScreen
import com.songseed.songseedcodex.ui.screens.LooseRhymeDrillScreen
import com.songseed.songseedcodex.ui.screens.LooseRhymesSelectionScreen
import com.songseed.songseedcodex.ui.screens.RhymeScreen
import com.songseed.songseedcodex.ui.screens.SettingsScreen
import com.songseed.songseedcodex.ui.screens.SlantRhymeScreen
import com.songseed.songseedcodex.ui.viewmodel.ImprovViewModel
import com.songseed.songseedcodex.ui.viewmodel.LooseRhymeViewModel
import com.songseed.songseedcodex.ui.viewmodel.RhymeViewModel
import com.songseed.songseedcodex.ui.viewmodel.SettingsViewModel
import com.songseed.songseedcodex.ui.viewmodel.SlantRhymeViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SongSeedCodexApp(
    improvRepository: ImprovRepository,
    rhymeRepository: RhymeRepository,
    slantRhymeRepository: SlantRhymeRepository,
    looseRhymeRepository: LooseRhymeRepository,
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
                onLooseRhymesClick = { navController.navigate(Routes.LooseRhymes) },
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

        composable(Routes.LooseRhymes) {
            LooseRhymesSelectionScreen(
                onBack = { navController.popBackStack() },
                onDefaultClick = { navController.navigate(Routes.SlantRhyme) },
                onAllClick = {
                    navController.navigate(Routes.looseRhymeDrill(LooseRhymeDrillSource.All.routeValue))
                },
                onHardEndingShiftClick = {
                    navController.navigate(Routes.HardEndingShiftDescription)
                }
            )
        }

        composable(Routes.HardEndingShiftDescription) {
            HardEndingShiftDescriptionScreen(
                onBack = { navController.popBackStack() },
                onStartDrill = {
                    navController.navigate(
                        Routes.looseRhymeDrill(LooseRhymeDrillSource.HardEndingShift.routeValue)
                    )
                }
            )
        }

        composable(
            route = Routes.LooseRhymeDrill,
            arguments = listOf(navArgument("source") { type = NavType.StringType })
        ) { backStackEntry ->
            val source = LooseRhymeDrillSource.fromRouteValue(
                backStackEntry.arguments?.getString("source")
            )
            val viewModel: LooseRhymeViewModel = viewModel(
                factory = LooseRhymeViewModel.factory(
                    source,
                    looseRhymeRepository,
                    settingsRepository
                )
            )
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            LooseRhymeDrillScreen(
                state = state,
                onNextWord = viewModel::generateNextPair,
                onShowExample = viewModel::showExample
            )
        }

        composable(Routes.SlantRhyme) {
            val viewModel: SlantRhymeViewModel = viewModel(
                factory = SlantRhymeViewModel.factory(slantRhymeRepository, settingsRepository)
            )
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            SlantRhymeScreen(
                state = state,
                onBack = { navController.popBackStack() },
                onNextWord = viewModel::generateNextPair,
                onShowExample = viewModel::showExample
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
