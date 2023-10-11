package me.alexdevs.tictactoe.core.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import me.alexdevs.tictactoe.core.util.Screen
import me.alexdevs.tictactoe.feature_tictactoe.presentation.home.HomeScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.alexdevs.tictactoe.feature_tictactoe.presentation.game.GameScreen
import me.alexdevs.tictactoe.feature_tictactoe.presentation.history.HistoryScreen
import me.alexdevs.tictactoe.feature_tictactoe.presentation.result.ResultScreen

@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate
            )
        }

        composable(Screen.GameScreen.route) {
            GameScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
            )
        }

        composable(Screen.HistoryScreen.route) {
            HistoryScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
            )
        }

        composable(Screen.ResultScreen.route) {
            ResultScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
            )
        }
    }
}