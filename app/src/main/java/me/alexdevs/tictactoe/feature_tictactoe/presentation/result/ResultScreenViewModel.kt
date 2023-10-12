package me.alexdevs.tictactoe.feature_tictactoe.presentation.result

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import me.alexdevs.tictactoe.core.util.UiEvent
import javax.inject.Inject

class ResultScreenViewModel @Inject constructor(

): ViewModel() {

    private val _state = mutableStateOf(ResultState())
    val state: State<ResultState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
}
