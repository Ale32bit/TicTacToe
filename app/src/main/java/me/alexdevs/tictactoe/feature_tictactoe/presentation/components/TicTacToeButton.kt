package me.alexdevs.tictactoe.feature_tictactoe.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.alexdevs.tictactoe.feature_tictactoe.presentation.game.GameRound
import me.alexdevs.tictactoe.ui.theme.CircleColor
import me.alexdevs.tictactoe.ui.theme.CrossColor
import me.alexdevs.tictactoe.ui.theme.DarkBlue

@Composable
fun TicTacToeButton(state: GameRound.Player, onCellClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(125.dp)
            .background(Color.White)
            .border(5.dp, DarkBlue)
            .clip(MaterialTheme.shapes.medium)
            .clickable { onCellClick() },
        contentAlignment = Alignment.Center
    ) {
        val playerState = getPlayerState(state)

        Text(
            color = playerState.first,
            text = playerState.second,
            fontSize = 75.sp,
        )
    }
}

/**
 * Gets the player state information based on the provided [state].
 *
 * @param state The current player state (Cross or Circle).
 * @return A [Pair] containing the player's color and name.
 */
private fun getPlayerState(state: GameRound.Player): Pair<Color, String> {
    var currentPlayer = ""
    var color = Color.White

    if (state == GameRound.Player.Cross) {
        currentPlayer = "X"
        color = CrossColor
    } else if (state == GameRound.Player.Circle) {
        currentPlayer = "O"
        color = CircleColor
    }

    return Pair(color, currentPlayer)
}