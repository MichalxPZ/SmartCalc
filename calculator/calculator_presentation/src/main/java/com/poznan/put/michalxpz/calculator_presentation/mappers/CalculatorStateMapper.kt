package com.poznan.put.michalxpz.calculator_presentation.mappers

import calculator.CalculatorInstance
import com.poznan.put.michalxpz.calculator_presentation.State

fun CalculatorInstance.toCalculatorState(): State {
    return State(
        input = this.input,
        result = this.result,
        errorMessage = this.errorMessage
    )
}

fun State.toCalculatorInstance(): CalculatorInstance {
    return CalculatorInstance(
        input = this.input,
        result = this.result,
        errorMessage = null
    )
}