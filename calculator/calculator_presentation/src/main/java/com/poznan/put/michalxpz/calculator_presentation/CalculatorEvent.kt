package com.poznan.put.michalxpz.calculator_presentation

sealed class CalculatorEvent {
    class NumberClicked(val number: String) : CalculatorEvent()
    class OperatorClicked(val operator: String) : CalculatorEvent()
    object ChangeSingClicked : CalculatorEvent()
    object DotClicked : CalculatorEvent()
    object EqualsClicked : CalculatorEvent()
    object AllClearCLicked : CalculatorEvent()
    object OnError : CalculatorEvent()
}