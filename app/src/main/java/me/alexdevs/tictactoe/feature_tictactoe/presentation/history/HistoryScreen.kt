package me.alexdevs.tictactoe.feature_tictactoe.presentation.history

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HistoryScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
) {
    Text(text = "History Screen")
}