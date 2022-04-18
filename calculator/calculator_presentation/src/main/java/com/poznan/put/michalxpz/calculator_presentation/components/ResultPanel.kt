package com.poznan.put.michalxpz.calculator_presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.poznan.put.michalxpz.core_ui.LocalSpacing

@Composable
fun ResultPanel(
    input: String,
    result: String,
    errorMessage: String? = null
) {
    val spacing = LocalSpacing.current
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(spacing.mediumLarge)
        ) {
            Column {
                Text(
                    text = buildAnnotatedString {
                        input.forEach { symbol ->
                            if ( listOf("/", "X", "-", "+", "%").contains(symbol.toString())) {
                                withStyle(SpanStyle( color = Color.Red.copy(alpha = 0.8f))) { append(symbol) }
                            } else {
                                append(symbol)
                            }
                        }
                    },
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.h4,
                    color = Color.LightGray
                )
                if (errorMessage != null) {
                    Text(
                        text = errorMessage,
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.h1,
                        color = Color.Red
                    )
                } else {
                    Text(
                        text = result,
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.h1,
                    )
                }
            }

        }
    }
}