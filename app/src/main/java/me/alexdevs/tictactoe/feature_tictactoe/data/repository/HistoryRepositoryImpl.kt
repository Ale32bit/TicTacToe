package me.alexdevs.tictactoe.feature_tictactoe.data.repository

import kotlinx.coroutines.flow.Flow
import me.alexdevs.tictactoe.feature_tictactoe.data.data_source.HistoryDao
import me.alexdevs.tictactoe.feature_tictactoe.domain.model.History
import me.alexdevs.tictactoe.feature_tictactoe.domain.repository.HistoryRepository
import javax.inject.Singleton

@Singleton
class HistoryRepositoryImpl(
    private val dao: HistoryDao
): HistoryRepository {

    override fun getHistories(): Flow<List<History>> {
        return dao.getHistories()
    }
}