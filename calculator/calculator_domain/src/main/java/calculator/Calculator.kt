package calculator

import MutableStack
import exceptions.CalculationException
import mutableStackOf

class Calculator {
    private var stack = MutableStack<String>()

    fun push(value: String) {
        stack.push(value)
    }

    fun drop() {
        stack = mutableStackOf()
    }

    suspend fun calculate() : String {
        val operator = stack.pop()
        val arg2 = stack.pop()
        val arg1 = stack.pop()

        return when(operator){
            "+" -> {
                (arg1.toDouble() + arg2.toDouble()).toString()
            }
            "-" -> {
                (arg1.toDouble() - arg2.toDouble()).toString()
            }
            "%" -> {
                (arg1.toDouble() / arg2.toDouble() * 100).toString()
            }
            "X" -> {
                (arg1.toDouble() * arg2.toDouble()).toString()
            }
            "/" -> {
                (arg1.toDouble() / arg2.toDouble()).toString()
            }

            else -> { throw CalculationException() }
        }
    }
}