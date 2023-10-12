package me.alexdevs.tictactoe.feature_tictactoe.presentation.game

data class GameState(
    val isInGame: Boolean = true,
    var gameRound: GameRound = GameRound(),
)
