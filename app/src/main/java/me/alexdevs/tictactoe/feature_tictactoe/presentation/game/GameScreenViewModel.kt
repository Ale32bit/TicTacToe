package me.alexdevs.tictactoe.feature_tictactoe.presentation.game

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
                if (_state.value.gameRound.canPlayCell(event.index)) {
                    val hasWon = _state.value.gameRound.playTurn(event.index)

                    if (!hasWon && _state.value.gameRound.isDraw() || hasWon) {
                        _state.value = _state.value.copy(
                            isInGame = !_state.value.isInGame
                        )
                    }
                }
            }
        }
    }
}