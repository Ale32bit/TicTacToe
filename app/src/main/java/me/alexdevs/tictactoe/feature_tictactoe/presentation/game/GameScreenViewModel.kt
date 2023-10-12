package me.alexdevs.tictactoe.feature_tictactoe.presentation.game

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import me.alexdevs.tictactoe.core.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor(

): ViewModel() {

    private val _state = mutableStateOf(GameState())
    val state: State<GameState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: GameEvent) {
        when(event) {
            is GameEvent.ToggleClick -> {
                val cont = playTurn(event.index)

                Log.d("GameScreenViewModel", event.mode ?: "Mode is NULL")

                if(cont && event.mode == "1" && _state.value.gameRound.playerTurn == GameRound.Player.Circle) {
                    val bestMove = _state.value.gameRound.bestMove()
                    playTurn(bestMove)
                }
            }
        }
    }

    private fun playTurn(index: Int): Boolean {
        if (_state.value.gameRound.canPlayCell(index)) {
            val hasWon = _state.value.gameRound.playTurn(index)

            if (!hasWon && _state.value.gameRound.isDraw() || hasWon) {
                _state.value = _state.value.copy(
                    isInGame = !_state.value.isInGame
                )
                return false
            }
        }
        return true
    }
}