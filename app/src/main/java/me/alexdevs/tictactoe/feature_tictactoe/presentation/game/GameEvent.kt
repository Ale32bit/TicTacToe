package me.alexdevs.tictactoe.feature_tictactoe.presentation.game

sealed class GameEvent {
    data object ShowHistory: GameEvent()
    data object SinglePlayer: GameEvent()
    data object MultiplePlayer: GameEvent()
}
