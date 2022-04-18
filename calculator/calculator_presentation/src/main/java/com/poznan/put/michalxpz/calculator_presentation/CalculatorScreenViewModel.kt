package com.poznan.put.michalxpz.calculator_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import util.UiEvent
import javax.inject.Inject

@HiltViewModel
class CalculatorScreenViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(State())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event : CalculatorEvent) {
        when(event) {
            is CalculatorEvent.SymbolClicked -> {
                state = state.copy(input = state.input + " " + event.symbol)
            }
        }
    }

}