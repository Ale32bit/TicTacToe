package me.alexdevs.tictactoe.feature_tictactoe.presentation.game

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import me.alexdevs.tictactoe.R
import me.alexdevs.tictactoe.core.presentation.util.asString
import me.alexdevs.tictactoe.core.util.UiEvent
import me.alexdevs.tictactoe.feature_tictactoe.presentation.components.TicTacToeButton

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

    Log.d("GameScreen", mode.toString())

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
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Winner:",
                style = TextStyle(fontSize = 24.sp),
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = if(winnerName != GameRound.Player.None) {
                    winnerName.toString()
                } else {
                    stringResource(id = R.string.draw_txt)
                },
                style = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )


            Button(
                onClick = onNavigateUp,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.go_back))
            }
        }
    }
}

