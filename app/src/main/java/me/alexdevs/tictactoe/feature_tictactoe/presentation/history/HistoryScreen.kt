package me.alexdevs.tictactoe.feature_tictactoe.presentation.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import me.alexdevs.tictactoe.feature_tictactoe.presentation.components.CreateBoxedBackButton
import me.alexdevs.tictactoe.feature_tictactoe.presentation.components.CreateText
import me.alexdevs.tictactoe.feature_tictactoe.presentation.components.TableCell
import me.alexdevs.tictactoe.ui.theme.Black
import me.alexdevs.tictactoe.ui.theme.White

@Composable
fun HistoryScreen(
    onNavigateUp: () -> Unit = {},
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    CreateBoxedBackButton(
        onClick = { onNavigateUp.invoke() },
    )
    CreateText(
        "Game\nHistory",
        Black,
        fontSize = 96.sp,
        alignment = Alignment.TopCenter,
        padding = PaddingValues(top = 50.dp)
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {

        val currentPadding = PaddingValues(30.dp, 300.dp, 30.dp, 30.dp)

        // Each cell of a column must have the same weight.
        val column1Weight = .30f // 25%
        val column2Weight = .70f // 50%
        // The LazyColumn will be our table. Notice the use of the weights below
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(currentPadding)
                .align(Alignment.Center)
        ) {
            items(state.histories) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(White)
                ) {
                    TableCell(text = it.winner, weight = column1Weight)
                    TableCell(text = it.date, weight = column2Weight)
                }
            }
        }
    }
}