package me.alexdevs.tictactoe.feature_tictactoe.presentation.home

sealed class HomeEvent {
    data object ShowHistory: HomeEvent()
}