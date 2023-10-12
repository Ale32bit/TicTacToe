package me.alexdevs.tictactoe.feature_tictactoe.presentation.home

sealed class HomeEvent {

    data object StartGameSinglePlayer: HomeEvent()
    data object StartGameMultiplePlayer: HomeEvent()
    data object ShowHistory: HomeEvent()
}