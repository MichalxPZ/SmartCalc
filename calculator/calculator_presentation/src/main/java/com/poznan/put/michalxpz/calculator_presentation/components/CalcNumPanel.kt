package com.poznan.put.michalxpz.calculator_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.poznan.put.michalxpz.calculator_presentation.CalculatorScreenViewModel
import com.poznan.put.michalxpz.core_ui.LocalSpacing

@Composable
fun CalcNumberPanel(
    viewModel: CalculatorScreenViewModel
) {
    val spacing = LocalSpacing.current

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .background(MaterialTheme.colors.secondary)
                .padding(spacing.mediumLarge)
        ) {
            Column {
                CalcRow(
                    texts = listOf("AC", "+/-", "%", "/"),
                    colors = listOf(Color.Cyan, Color.Cyan, Color.Cyan, Color.Red),
                    weights = listOf(0.25f, 0.25f, 0.25f, 0.25f),
                    viewModel
                )
                Spacer(modifier = Modifier.height(spacing.small))

                CalcRow(
                    texts = listOf("7", "8", "9", "X"),
                    colors = listOf(Color.LightGray, Color.LightGray, Color.LightGray, Color.Red),
                    weights = listOf(0.25f, 0.25f, 0.25f, 0.25f),
                    viewModel
                )
                Spacer(modifier = Modifier.height(spacing.small))

                CalcRow(
                    texts = listOf("4", "5", "6", "-"),
                    colors = listOf(Color.LightGray, Color.LightGray, Color.LightGray, Color.Red),
                    weights = listOf(0.25f, 0.25f, 0.25f, 0.25f),
                    viewModel
                )
                Spacer(modifier = Modifier.height(spacing.small))

                CalcRow(
                    texts = listOf("1", "2", "3", "+"),
                    colors = listOf(Color.LightGray, Color.LightGray, Color.LightGray, Color.Red),
                    weights = listOf(0.25f, 0.25f, 0.25f, 0.25f),
                    viewModel
                )
                Spacer(modifier = Modifier.height(spacing.small))

                CalcRow(
                    texts = listOf("0", ".", "_"),
                    colors = listOf(Color.LightGray, Color.LightGray, Color.LightGray, Color.Red),
                    weights = listOf(0.33f, 0.33f, 0.33f),
                    viewModel
                )
            }
        }
    }
}