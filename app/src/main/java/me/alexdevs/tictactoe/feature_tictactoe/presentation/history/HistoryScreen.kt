package me.alexdevs.tictactoe.feature_tictactoe.presentation.history

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HistoryScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: HistoryViewModel = hiltViewModel()
) {
    Text("history screen")
}