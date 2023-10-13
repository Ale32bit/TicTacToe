package me.alexdevs.tictactoe.feature_tictactoe.presentation.game

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import me.alexdevs.tictactoe.core.util.UiEvent
import me.alexdevs.tictactoe.feature_tictactoe.domain.model.History
import me.alexdevs.tictactoe.feature_tictactoe.domain.use_case.HistoryUseCases
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameScreenViewModel @Inject constructor(
    private val historiesUseCases: HistoryUseCases
): ViewModel() {

    private val _state = mutableStateOf(GameState())
    val state: State<GameState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: GameEvent) {
        when(event) {
            is GameEvent.ToggleClick -> {
                val cont = playTurn(event.index)

                if(cont && _state.value.gameRound.playerTurn == GameRound.Player.Circle) {
                    var nextMove: Int
                    val chance = Random.nextDouble()
                    Log.d("GameScreenViewModel", "Difficulty: ${_state.value.difficulty}, Minimax Chance: $chance; Minimax: ${chance < _state.value.difficulty}")
                    if (chance < _state.value.difficulty) {
                        nextMove = _state.value.gameRound.bestMove()
                    } else {
                        do {
                            nextMove = Random.nextInt(0, 9)
                        } while (!_state.value.gameRound.canPlayCell(nextMove))
                    }
                    playTurn(nextMove)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun playTurn(index: Int): Boolean {
        if (_state.value.gameRound.canPlayCell(index)) {
            val hasWon = _state.value.gameRound.playTurn(index)

            if (!hasWon && _state.value.gameRound.isDraw() || hasWon) {
                _state.value = _state.value.copy(
                    isInGame = !_state.value.isInGame
                )

                val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")

                historiesUseCases.insertGame(
                    History(
                        date = formatter.format(LocalDateTime.now()),
                        winner = if(_state.value.gameRound.winner == GameRound.Player.Cross) "X" else "O"
                    )
                )

                return false
            }
        }
        return true
    }
}