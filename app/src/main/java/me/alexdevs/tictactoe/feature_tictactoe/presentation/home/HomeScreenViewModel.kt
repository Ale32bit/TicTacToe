package me.alexdevs.tictactoe.feature_tictactoe.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(

): ViewModel() {

    init {

    }

    fun onEvent(event: HomeEvent) {
        when(event) {
            else -> {}
        }
    }
}