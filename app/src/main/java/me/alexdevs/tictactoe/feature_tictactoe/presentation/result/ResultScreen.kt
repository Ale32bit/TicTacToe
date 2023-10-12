package me.alexdevs.tictactoe.feature_tictactoe.presentation.result


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.flow.collectLatest
import me.alexdevs.tictactoe.core.presentation.util.asString
import me.alexdevs.tictactoe.core.util.UiEvent
import me.alexdevs.tictactoe.feature_tictactoe.presentation.game.GameScreen
import me.alexdevs.tictactoe.feature_tictactoe.presentation.game.GameScreenViewModel

@Composable
fun ResultScreen(
    winnerName: String, // Nome del vincitore
    onNavigateUp: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
    viewModel: ResultScreenViewModel = hiltViewModel()
) {
    /**
     * NON TOCCARE !!!!!!!!
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

        if (winnerName != null) {
            Text(
                text = winnerName,
                style = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            Text(
                text = "Draw",
                style = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )
        }

        // Button(
        //    onClick = onNavigateUp,
        //    modifier = Modifier.padding(top = 16.dp)
        // ) {
        //    Text(text = "Back to Main Menu")
        // }
    }
}
