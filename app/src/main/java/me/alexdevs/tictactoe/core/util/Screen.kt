package me.alexdevs.tictactoe.core.util

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object GameScreen: Screen("game_screen")
    object ResultScreen: Screen("result_screen")
    object HistoryScreen: Screen("history_screen")

}