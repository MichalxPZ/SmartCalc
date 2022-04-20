package calculator

import MutableStack
import android.util.Log
import exceptions.CalculationException
import mutableStackOf
import util.SharedPrefs
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
        val symbols = input.split(' ').filter { it.isNotEmpty() }
        val operator = symbols.last()
        symbols.filter { isNumber(it) }.forEach { stack.push(it.toDouble()) }
        Log.i("RESULT", stack.toString())
        val arg2 = stack.pop()
        val arg1 = stack.pop()
        val result = parseOperation(arg2, arg1, operator)
        calculatorState = if (result.rem(1).equals(0.0) || SharedPrefs.round == 0) {
            calculatorState.copy(
                input = "$stack $result ",
                result = result.toInt().toString()
            )
        } else {
            val roundingMode = RoundingMode.DOWN
            val decimalFormat = DecimalFormat("#.${"#".repeat(SharedPrefs.round)}")
            decimalFormat.roundingMode = roundingMode
            calculatorState.copy(
                input = "$stack $result ",
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