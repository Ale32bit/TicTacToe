package me.alexdevs.tictactoe.feature_tictactoe.presentation.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen() {
    val gameRound by remember { mutableStateOf(GameRound()) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

        }

        LazyColumn(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            items(3) { rowIndex ->
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    state = rememberLazyListState()
                ) {
                    items(3) { colIndex ->
                        val index = rowIndex * 3 + colIndex
                        TicTacToeButton(
                            state = gameRound.grid[index],
                            onCellClick = {
                                if (gameRound.canPlayCell(index)) {
                                    val hasWon = gameRound.playTurn(index)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TicTacToeButton(state: GameRound.Player, onCellClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.White)
            .border(5.dp, Color.Blue)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onCellClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = when (state) {
                GameRound.Player.Cross -> "X"
                GameRound.Player.Circle -> "O"
                else -> ""
            },
            fontSize = 60.sp
        )
    }
}

