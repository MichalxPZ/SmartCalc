package com.poznan.put.michalxpz.calculator_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.poznan.put.michalxpz.core_ui.LocalSpacing

@Composable
fun CalcButton(
    text: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.h6,
    textColor: Color = MaterialTheme.colors.onBackground,
    buttonColor: Color = MaterialTheme.colors.secondary
    ) {
    val spacing = LocalSpacing.current
    Button(
        enabled = isEnabled,
        onClick = { onClick(text) },
        shape = CircleShape,
        modifier = modifier
            .background(Color(buttonColor.red, buttonColor.green, buttonColor.blue, 1f))
            .shadow(spacing.mediumSmall)
    ) {
        Text(
           text = text,
           style = textStyle,
           color = textColor,
            modifier = Modifier.padding(spacing.small)
        )
    }
}