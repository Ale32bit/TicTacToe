package me.alexdevs.tictactoe.feature_tictactoe.presentation.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.alexdevs.tictactoe.feature_tictactoe.domain.use_case.HistoryUseCases
import javax.inject.Inject


@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyUseCases: HistoryUseCases
): ViewModel() {

    private val _state = mutableStateOf(HistoryState())
    val state: State<HistoryState> = _state

    private var getHistoriesJob: Job? = null

    init {
        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
            getHistoriesJob?.cancel()
            getHistoriesJob = historyUseCases.getHistories()
                .onEach { history ->
                    _state.value = state.value.copy(
                        histories = history.sortedByDescending { it.id }
                    )
                }
                .launchIn(viewModelScope)
        }
    }
}