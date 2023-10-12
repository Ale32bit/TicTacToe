package me.alexdevs.tictactoe.feature_tictactoe.domain.repository

import kotlinx.coroutines.flow.Flow
import me.alexdevs.tictactoe.feature_tictactoe.domain.model.History

interface HistoryRepository {

    fun getHistories(): Flow<List<History>>


}