package me.alexdevs.tictactoe.feature_tictactoe.presentation.game

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun GameScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    mode: String? = null,
) {
    Text(text = "Game Screen")
}