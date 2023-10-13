package me.alexdevs.tictactoe.feature_tictactoe.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import me.alexdevs.tictactoe.R
import me.alexdevs.tictactoe.core.presentation.util.asString
import me.alexdevs.tictactoe.core.util.Screen
import me.alexdevs.tictactoe.core.util.UiEvent
import me.alexdevs.tictactoe.feature_tictactoe.presentation.components.CreateButton
import me.alexdevs.tictactoe.feature_tictactoe.presentation.components.CreateText
import me.alexdevs.tictactoe.ui.theme.Black

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    CreateText("Tic\nTac\nToe", Black, 120.sp)

    /**
     * !!! NON TOCCARE !!! ==> Gestisce gli eventi del viewModel come redirection o mostrare un popup
     */
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    Toast.makeText(
                        context,
                        event.uiText.asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is UiEvent.Navigate -> {
                    onNavigate(event.route)
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        val yOffset = 80.dp
        val offsetBetweenButtons = 8.dp

        CreateButton(
            customText = stringResource(id = R.string.one_player),
            fontSize = 32.sp,
            onClick = {
                onNavigate(Screen.GameScreen.route)
            },
            yPosition = yOffset,
        )

        CreateButton(
            customText = stringResource(id = R.string.two_players),
            fontSize = 32.sp,
            onClick = {
                onNavigate(Screen.TwoGameScreen.route)
            },
            yPosition = (yOffset * 2) + offsetBetweenButtons,
        )

        CreateButton(
            customText = stringResource(id = R.string.show_history),
            fontSize = 32.sp,
            onClick = {
                onNavigate(Screen.HistoryScreen.route)
            },
            yPosition = (yOffset * 3) + (offsetBetweenButtons * 2),
        )
    }
}