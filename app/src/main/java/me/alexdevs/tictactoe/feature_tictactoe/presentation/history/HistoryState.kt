package me.alexdevs.tictactoe.feature_tictactoe.presentation.history

import me.alexdevs.tictactoe.feature_tictactoe.domain.model.History

data class HistoryState(
    val histories: List<History> = emptyList()
)
