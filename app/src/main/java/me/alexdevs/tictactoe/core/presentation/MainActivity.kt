package me.alexdevs.tictactoe.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.alexdevs.tictactoe.core.presentation.components.Navigation
import me.alexdevs.tictactoe.core.presentation.components.StandardScaffold
import me.alexdevs.tictactoe.ui.theme.DarkBlue
import me.alexdevs.tictactoe.ui.theme.LightBlue
import me.alexdevs.tictactoe.ui.theme.TicTacToeTheme
import me.alexdevs.tictactoe.ui.theme.White

/**
 * The main activity of the Tic Tac Toe game application.
 *
 * This activity serves as the entry point for the app and sets up the Compose UI.
 *
 * @constructor Creates a new instance of the [MainActivity].
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is starting. This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     * this Bundle contains the data it most recently supplied in [onSaveInstanceState].
     * Note: Otherwise, it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TicTacToeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    StandardScaffold(
                        modifier = Modifier.fillMaxSize(),
                        content = {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .gradientBackground(listOf(DarkBlue, LightBlue, White))
                                )
                            }
                        }
                    ) {
                        Navigation(navController)
                    }
                }
            }
        }
    }

    /**
     * Adds a gradient background to the given [Modifier].
     *
     * @param colors The list of colors for the gradient.
     * @return The modified [Modifier] with a gradient background.
     */
    private fun Modifier.gradientBackground(colors: List<Color>) = this.then(
        Modifier.drawBehind {
            // Draw a rectangle with a linear gradient from top to bottom
            drawRect(
                brush = Brush.linearGradient(
                    colors = colors,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height)
                ),
                size = size
            )
        }
    )
}