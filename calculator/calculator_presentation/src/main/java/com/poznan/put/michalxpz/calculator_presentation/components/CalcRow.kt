package com.poznan.put.michalxpz.calculator_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.poznan.put.michalxpz.calculator_presentation.CalculatorEvent
import com.poznan.put.michalxpz.calculator_presentation.CalculatorScreenViewModel
import com.poznan.put.michalxpz.core_ui.LocalSpacing

@Composable
fun CalcRow(
    texts: List<String>,
    colors: List<Color>,
    weights: List<Float>,
    viewModel: CalculatorScreenViewModel
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.secondary)
    ) {
        texts.forEachIndexed { index, text ->
            CalcButton(
                text = text,
                onClick = {
                    when(text) {
                       in "+", "/", "X", "-", "%" -> {
                           viewModel.onEvent(CalculatorEvent.OperatorClicked(text))
                       }
                        in "0".."9" -> {
                            viewModel.onEvent(CalculatorEvent.NumberClicked(text))
                        }
                        "AC" -> { viewModel.onEvent(CalculatorEvent.AllClearCLicked) }
                        "+/-" -> { viewModel.onEvent(CalculatorEvent.ChangeSingClicked) }
                        "=" -> { viewModel.onEvent(CalculatorEvent.EqualsClicked) }
                        "." -> { viewModel.onEvent(CalculatorEvent.DotClicked) }
                    }
                },
                textColor = colors[index],
                modifier = Modifier.weight(weights[index])
            )
            if (index != texts.lastIndex) {
                Spacer(modifier = Modifier.width(spacing.small))
            }
        }
    }
}