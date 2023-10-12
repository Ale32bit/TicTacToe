package me.alexdevs.tictactoe.feature_tictactoe.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import me.alexdevs.tictactoe.feature_tictactoe.domain.model.History

@Database(
    entities = [History::class],
    version = 1,
    exportSchema = true,
)
abstract class HistoryDatabase: RoomDatabase() {

    abstract val historyDao: HistoryDao

    companion object {
        const val DATABASE_NAME = "history_db"
    }
}