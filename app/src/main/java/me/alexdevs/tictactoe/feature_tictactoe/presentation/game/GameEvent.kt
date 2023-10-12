package me.alexdevs.tictactoe.feature_tictactoe.presentation.game

sealed class GameEvent{
    data class ToggleClick(val index: Int): GameEvent()
}
