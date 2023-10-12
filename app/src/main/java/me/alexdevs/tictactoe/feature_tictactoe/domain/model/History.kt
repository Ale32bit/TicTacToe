package me.alexdevs.tictactoe.feature_tictactoe.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(
    var date: String,
    var winner: String,
    @PrimaryKey
    val id: Int? = null,
)
