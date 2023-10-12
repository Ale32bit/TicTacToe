package me.alexdevs.tictactoe.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.alexdevs.tictactoe.feature_tictactoe.data.data_source.HistoryDatabase
import me.alexdevs.tictactoe.feature_tictactoe.data.repository.HistoryRepositoryImpl
import me.alexdevs.tictactoe.feature_tictactoe.domain.repository.HistoryRepository
import me.alexdevs.tictactoe.feature_tictactoe.domain.use_case.GetHistories
import me.alexdevs.tictactoe.feature_tictactoe.domain.use_case.HistoryUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHistoryDatabase(app: Application): HistoryDatabase {
        return Room.databaseBuilder(
            app,
            HistoryDatabase::class.java,
            HistoryDatabase.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: HistoryDatabase): HistoryRepository {
        return HistoryRepositoryImpl(db.historyDao)
    }

    @Provides
    @Singleton
    fun provideHistoryUseCases(repository: HistoryRepository): HistoryUseCases {
        return HistoryUseCases(
            getHistories = GetHistories(repository)
        )
    }
}