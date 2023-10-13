package me.alexdevs.tictactoe.feature_tictactoe.presentation.game

import android.util.Log
import kotlin.random.Random

data class GameState(
    val isInGame: Boolean = true,
    var gameRound: GameRound = GameRound(),
    val difficulty: Float = getRandomDifficulty()
)

fun getRandomDifficulty(): Float {
    var difficulty = Random.nextFloat()
    if(difficulty > 0.75f)
        difficulty = 1.0f

    Log.d("GameState", "Difficulty chosen: $difficulty")

    return difficulty
}


