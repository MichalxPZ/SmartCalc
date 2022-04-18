package com.poznan.put.michalxpz.calculator_presentation

sealed class CalculatorEvent {
    class SymbolClicked(val symbol: String) : CalculatorEvent()
}