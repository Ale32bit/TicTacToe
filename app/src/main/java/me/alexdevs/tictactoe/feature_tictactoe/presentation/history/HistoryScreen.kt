package me.alexdevs.tictactoe.feature_tictactoe.presentation.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import me.alexdevs.tictactoe.ui.theme.Black
import me.alexdevs.tictactoe.ui.theme.CircleColor
import me.alexdevs.tictactoe.ui.theme.CreateText
import me.alexdevs.tictactoe.ui.theme.CrossColor

@Composable
fun HistoryScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: HistoryViewModel = hiltViewModel()
) {
    // CreateText("History", Black, fontSize = 96.sp, alignment = Alignment.TopCenter)
}