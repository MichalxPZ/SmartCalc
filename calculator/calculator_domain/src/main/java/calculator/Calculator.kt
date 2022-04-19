package calculator

import MutableStack
import android.util.Log
import exceptions.CalculationException
import mutableStackOf
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.round

class Calculator {
    private var stack = MutableStack<Double>()
    var calculatorState = CalculatorInstance()

    companion object {
        private val operations = listOf("+", "-", "X", "/", "%")
    }

    fun calculate(input: String) : CalculatorInstance {
        stack = mutableStackOf()
        if (input.isEmpty()) throw IllegalArgumentException()
        val negativeSign = input.first() == '-'
        val symbols = input.split(' ').filter { it.isNotEmpty() }
        val (arg1, arg2, operator) = symbols.takeLast(3)
        val result = parseOperation(arg2.toDouble(), arg1.toDouble(), operator)
        calculatorState = if (result.rem(1).equals(0.0)) {
            calculatorState.copy(
                input = result.toString(),
                result = result.toInt().toString()
            )
        } else {
            val ROUND = 2
            val roundingMode = RoundingMode.DOWN
            val decimalFormat = DecimalFormat("#.${"#".repeat(ROUND)}")
            decimalFormat.roundingMode = roundingMode
            calculatorState.copy(
                input = result.toString(),
                result = decimalFormat.format(result).toString()
            )
        }
        Log.i("RESULT", calculatorState.toString())
        return calculatorState
    }

    private fun parseOperation(second: Double?, first: Double?, operation: String) =
        when (operation) {
        "+" -> first!! + second!!
        "-" -> first!! - second!!
        "X" -> first!! * second!!
        "/" -> first!! / second!!
        "%" -> first!! / second!! * 100
        else -> throw CalculationException()
    }

    private fun isNumber(symbol: String): Boolean {
        try {
            symbol.toDouble()
        } catch (nfe: NumberFormatException) {
            return false
        }
        return true
    }

    private fun isOperator(symbol: String): Boolean = operations.contains(symbol)
}