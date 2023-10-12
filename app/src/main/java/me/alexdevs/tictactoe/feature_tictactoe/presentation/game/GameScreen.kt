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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.alexdevs.tictactoe.R
import me.alexdevs.tictactoe.ui.theme.CircleColor
import me.alexdevs.tictactoe.ui.theme.CrossColor

@Composable
fun GameScreen() {
    val gameRound by remember { mutableStateOf(GameRound()) }

    val annotatedTitle = buildAnnotatedString {
        withStyle(style = SpanStyle(color = CrossColor)) {
            append("X")
        }
        append(" vs ")
        withStyle(style = SpanStyle(color = CircleColor)) {
            append("O")
        }
    }


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
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = annotatedTitle,
                textAlign = TextAlign.Center,
                fontSize = 100.sp
            )
        }

        LazyColumn(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            items(3) { rowIndex ->
                LazyRow(
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
                                    if(!hasWon && gameRound.isDraw()) {
                                        // Game is draw, no one won. Game over.

                                    }
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
            .size(125.dp)
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

