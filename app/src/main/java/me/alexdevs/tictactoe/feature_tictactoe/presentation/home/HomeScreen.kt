package me.alexdevs.tictactoe.feature_tictactoe.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import me.alexdevs.tictactoe.R
import me.alexdevs.tictactoe.core.presentation.util.asString
import me.alexdevs.tictactoe.core.util.UiEvent

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current


    /**
     * !!! NON TOCCARE !!! ==> Gestisce gli eventi del viewModel come redirection o mostrare un popup
     */
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
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

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = {
            viewModel.onEvent(HomeEvent.StartGameSinglePlayer)
        }) {
            Text(text = "Button 1")
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(onClick = {
            viewModel.onEvent(HomeEvent.StartGameMultiplePlayer)
        }) {
            Text(text = "Button 2")
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(onClick = {
           viewModel.onEvent(HomeEvent.ShowHistory)
        }) {
            Text(text = stringResource(id = R.string.show_history))
        }
    }
}