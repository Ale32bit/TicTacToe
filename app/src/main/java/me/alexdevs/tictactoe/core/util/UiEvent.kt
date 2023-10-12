package me.alexdevs.tictactoe.core.util

sealed class UiEvent: Event() {
    data class ShowSnackbar(val uiText: UiText): UiEvent()
    data class Navigate(val route: String): UiEvent()
}