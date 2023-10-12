package me.alexdevs.tictactoe.feature_tictactoe.domain.use_case

import kotlinx.coroutines.flow.Flow
import me.alexdevs.tictactoe.feature_tictactoe.domain.model.History
import me.alexdevs.tictactoe.feature_tictactoe.domain.repository.HistoryRepository

class AddGame(
    private val repository: HistoryRepository
) {

    operator fun invoke(history: History) {
        repository.insertGame(history)
    }
}