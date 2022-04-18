package com.poznan.put.michalxpz.calculator_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import calculator.Calculator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import util.UiEvent
import javax.inject.Inject
import exceptions.CalculationException

@HiltViewModel
class CalculatorScreenViewModel @Inject constructor() : ViewModel() {

    private val calculator = Calculator()

    var state by mutableStateOf(State())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event : CalculatorEvent) {
        when(event) {
            is CalculatorEvent.NumberClicked -> {
                state = if (state.input.isNotEmpty()) {
                    state.copy(input = state.input + " " + event.number)
                } else {
                    state.copy(input = event.number)
                }
                calculator.push(event.number)
            }
            CalculatorEvent.AllClearCLicked -> {
                state = state.copy(input = "", result = "")
                calculator.drop()
            }
            CalculatorEvent.ChangeSingClicked -> {
                if (state.input.first().isDigit()) {
                    state = state.copy(input = "-" + state.input)
                } else if (state.input.first() == '-') {
                    state = state.copy(input =  state.input.substring(1))
                }
            }
            CalculatorEvent.DotClicked -> {

            }
            CalculatorEvent.EqualsClicked -> {
                calculate()
            }
            is CalculatorEvent.OperatorClicked -> {
                state = state.copy(input = state.input + " " + event.operator)
                calculator.push(event.operator)
            }
            CalculatorEvent.OnError -> {
                state = state.copy(errorMessage = "There was an error with your calculation!\nPlease restart")
            }
        }
    }

    private fun calculate() {
        viewModelScope.launch {
            try {
                val result = calculator.calculate()
                state = state.copy(
                    input = state.input.subSequence(1, state.input.length-3).toString() + result,
                    result = result
                )
            } catch(e : CalculationException) {
                this@CalculatorScreenViewModel.onEvent(CalculatorEvent.OnError)
        }
        }

    }

}