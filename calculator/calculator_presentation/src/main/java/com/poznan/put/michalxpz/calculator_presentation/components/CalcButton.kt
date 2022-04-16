package com.poznan.put.michalxpz.calculator_presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.poznan.put.michalxpz.core_ui.LocalSpacing

@Composable
fun CalcButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.button
) {
    val spacing = LocalSpacing.current
    Button(
        enabled = isEnabled,
        onClick = onClick,
        shape = CircleShape,
        modifier = modifier
    ) {
        Text(
           text = text,
           style = textStyle,
           color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(spacing.small)
        )
    }
}