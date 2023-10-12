package me.alexdevs.tictactoe.feature_tictactoe.data.data_source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import me.alexdevs.tictactoe.feature_tictactoe.domain.model.History

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history")
    fun getHistories(): Flow<List<History>>

    @Upsert
    fun addGame(history: History)
}