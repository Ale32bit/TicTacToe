package me.alexdevs.tictactoe.feature_tictactoe.presentation.game

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import me.alexdevs.tictactoe.R
import me.alexdevs.tictactoe.core.presentation.util.asString
import me.alexdevs.tictactoe.core.util.UiEvent
import me.alexdevs.tictactoe.feature_tictactoe.presentation.components.CreateButton
import me.alexdevs.tictactoe.feature_tictactoe.presentation.components.CreateText
import me.alexdevs.tictactoe.feature_tictactoe.presentation.components.TicTacToeButton
import me.alexdevs.tictactoe.ui.theme.Black
import me.alexdevs.tictactoe.ui.theme.CircleColor
import me.alexdevs.tictactoe.ui.theme.CrossColor

@Composable
fun GameScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    mode: String? = null,
    viewModel: GameScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    val winnerName = state.gameRound.winner

    /**
     * NON TOCCARE !!!!!!!!
     */
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    Toast.makeText(
                        context,
                        event.uiText!!.asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is UiEvent.Navigate -> {
                    onNavigate(event.route)
                }
            }
        }
    }

    if (state.isInGame) {

        CreateText(customText = "X", customColor = CrossColor, fontSize = 120.sp, alignment = Alignment.TopStart, padding = PaddingValues(start = 35.dp, top = 25.dp))
        CreateText(customText = " vs ", customColor = Black, fontSize = 120.sp, alignment = Alignment.TopCenter, padding = PaddingValues(top = 25.dp))
        CreateText(customText = "O", customColor = CircleColor, fontSize = 120.sp, alignment = Alignment.TopEnd, padding = PaddingValues(end = 25.dp, top = 25.dp))

        var player = ""
        var colorPlayer: Color = Black

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

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
                                state = state.gameRound.grid[index],
                                onCellClick = {
                                    viewModel.onEvent(GameEvent.ToggleClick(index, mode))
                                }
                            )
                        }
                    }
                }
            }
        }

        //CreateText(customText = "X", customColor = CrossColor, fontSize = 100.sp, alignment = Alignment.BottomStart, padding = PaddingValues(start = 50.dp, bottom = 15.dp))
        //CreateText(customText = "'s", customColor = CrossColor, fontSize = 75.sp, alignment = Alignment.BottomStart, padding = PaddingValues(start = 125.dp, bottom = 15.dp))
        if(state.gameRound.playerTurn.toString().equals("Cross")){
            player = "X"
            colorPlayer = CrossColor
        }else if(state.gameRound.playerTurn.toString().equals("Circle")){
            player = "O"
            colorPlayer = CircleColor
        }

        CreateText(customText = player, customColor = colorPlayer, fontSize = 100.sp, alignment = Alignment.BottomStart, padding = PaddingValues(start = 50.dp, bottom = 50.dp))
        CreateText(customText = "'s", customColor = colorPlayer, fontSize = 75.sp, alignment = Alignment.BottomStart, padding = PaddingValues(start = 125.dp, bottom = 50.dp))
        CreateText(customText = " turn", customColor = Black, fontSize = 75.sp, alignment = Alignment.BottomCenter, padding = PaddingValues(start = 140.dp,bottom = 50.dp))

    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val yOffset = 80.dp
            val offsetBetweenItems = 8.dp

            CreateText(
                customText = "POST\nMATCH",
                customColor = Color.Black,
                fontSize = 110.sp,
                alignment = Alignment.TopCenter
            )

            CreateText(
                customText = if (winnerName != GameRound.Player.None) {
                    when (winnerName) {
                        GameRound.Player.Circle -> "O"
                        GameRound.Player.Cross -> "X"
                        else -> " "
                    }
                } else {
                    stringResource(id = R.string.draw_txt)
                },
                customColor = if (winnerName == GameRound.Player.Circle) {
                    CircleColor
                } else {
                    CrossColor
                },
                fontSize = 110.sp,
            )

            CreateText(
                customText = if (winnerName != GameRound.Player.None) {
                    "WINS"
                } else {
                    ""
                },
                customColor = Color.Black,
                fontSize = 110.sp,
                alignment = Alignment.BottomCenter,
                padding = PaddingValues(10.dp,220.dp)
            )

            CreateButton(
                customText = stringResource(id = R.string.go_back),
                fontSize = 32.sp,
                onClick = { onNavigateUp.invoke() },
                yPosition = (yOffset * 4) + (offsetBetweenItems * 3)
            )
        }
    }
}

