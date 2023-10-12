package me.alexdevs.tictactoe.feature_tictactoe.data.data_source

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.alexdevs.tictactoe.feature_tictactoe.domain.model.History

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history")
    fun getHistories(): Flow<List<History>>
}