package me.alexdevs.tictactoe.feature_tictactoe.presentation.result

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ResultScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
) {
    Text(text = "Result Screen")
}