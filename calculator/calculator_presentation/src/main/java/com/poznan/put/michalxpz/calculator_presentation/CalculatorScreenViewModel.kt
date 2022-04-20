package com.poznan.put.michalxpz.calculator_presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import calculator.Calculator
import com.poznan.put.michalxpz.calculator_presentation.mappers.toCalculatorInstance
import com.poznan.put.michalxpz.calculator_presentation.mappers.toCalculatorState
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
                state = state.copy(input = state.input + event.number)
            }
            CalculatorEvent.AllClearCLicked -> {
                state = state.copy(input = "", result = "", errorMessage = null)
                calculator.calculatorState = state.toCalculatorInstance()
            }
            CalculatorEvent.ChangeSingClicked -> {
                if (state.input.isNotEmpty()) {
                    if (state.input.first().isDigit()) {
                        state = state.copy(input = "-" + state.input)
                    } else if (state.input.first() == '-') {
                        state = state.copy(input =  state.input.substring(1))
                    }
                } else {
                    state = state.copy(input =  "-")
                }
            }
            CalculatorEvent.DotClicked -> {
                state = if (state.input.isNotEmpty() && state.input.last().isDigit()) {
                    if (state.input.split(" ").takeLast(1).toString().contains(".")) {
                        state.copy(input =  state.input + "0 0.")
                    } else {
                        state.copy(input =  state.input + ".")
                    }
                } else {
                    if (state.input.isNotEmpty() && state.input.last().equals('.')) {
                        state.copy(input =  state.input + "0 0.")
                    } else {
                        state.copy(input =  state.input + "0.")
                    }
                }
            }
            CalculatorEvent.SpaceClicked -> {
                if (state.input.isNotEmpty() && state.input.last() != ' ') {
                    state = state.copy(input = state.input + " ")
                }
            }
            is CalculatorEvent.OperatorClicked -> {
                state = state.copy(input = state.input + " " + event.operator)
                calculator.calculatorState = state.toCalculatorInstance()
                calculate()
            }
            CalculatorEvent.OnError -> {
                state = state.copy(errorMessage = "There was an error with your calculation!\nPlease restart")
            }
        }
    }

    private fun calculate() {
        viewModelScope.launch {
            try {
                state = calculator.calculate(state.input).toCalculatorState()
                Log.i("State", "$state")
            } catch (e: Exception) {
                state = state.copy(errorMessage = "There was an error with your calculation!\nPlease restart")
            }
        }
    }

}