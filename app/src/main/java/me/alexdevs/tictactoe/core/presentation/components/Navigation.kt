package me.alexdevs.tictactoe.core.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import me.alexdevs.tictactoe.core.util.Screen
import me.alexdevs.tictactoe.feature_tictactoe.presentation.home.HomeScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.alexdevs.tictactoe.feature_tictactoe.presentation.game.GameScreen
import me.alexdevs.tictactoe.feature_tictactoe.presentation.history.HistoryScreen

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

        composable(
            route = Screen.GameScreen.route,
            arguments = listOf(
                navArgument(name = "mode") {
                    type = NavType.StringType
                    nullable = false
                    defaultValue = "1"
                }
            )
        ) {
            GameScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
                mode = it.arguments?.getString("mode")
            )
        }

        composable(Screen.HistoryScreen.route) {
            HistoryScreen(
                onNavigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
            )
        }
    }
}