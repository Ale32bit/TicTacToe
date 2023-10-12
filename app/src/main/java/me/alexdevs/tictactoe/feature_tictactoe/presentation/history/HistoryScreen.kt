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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import me.alexdevs.tictactoe.feature_tictactoe.presentation.components.CreateText
import me.alexdevs.tictactoe.feature_tictactoe.presentation.components.TableCell
import me.alexdevs.tictactoe.feature_tictactoe.presentation.components.TableScreen
import me.alexdevs.tictactoe.ui.theme.Black
import me.alexdevs.tictactoe.ui.theme.White
import kotlin.random.Random

@Composable
fun HistoryScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CreateText("Game\nHistory", Black, fontSize = 96.sp, alignment = Alignment.TopCenter)

        val currentPadding = PaddingValues(30.dp, 275.dp, 30.dp, 30.dp)

        // Just a fake data... a Pair of Int and String
        val tableData = (1..3).mapIndexed { index, item ->
            index to "Item $index"
        }
        // Each cell of a column must have the same weight.
        val column1Weight = .5f // 25%
        val column2Weight = .10f // 25%
        val column3Weight = .85f; // 50%
        // The LazyColumn will be our table. Notice the use of the weights below
        LazyColumn(
            Modifier.fillMaxSize()
                .padding(currentPadding)) {
            items(state.histories) {
                Row(
                    Modifier.fillMaxWidth()
                        .background(White)) {
                    TableCell(text = it.id.toString(), weight = column1Weight)
                    TableCell(text = it.winner, weight = column2Weight)
                    TableCell(text = it.date, weight = column3Weight)
                }
            }
        }
    }
}