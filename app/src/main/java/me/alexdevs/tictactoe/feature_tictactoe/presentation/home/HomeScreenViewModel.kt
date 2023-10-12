package me.alexdevs.tictactoe.feature_tictactoe.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import me.alexdevs.tictactoe.core.util.Screen
import me.alexdevs.tictactoe.core.util.UiEvent
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(

): ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.ShowHistory -> {
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.Navigate(Screen.HistoryScreen.route))
                }
            }
        }
    }
}