package me.alexdevs.tictactoe.feature_tictactoe.presentation.game

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor(

): ViewModel() {

    init {

    }

    fun onEvent(event: GameEvent) {
        when(event) {
            else -> Unit
        }
    }
}