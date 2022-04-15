package com.poznan.put.michalxpz.smartcalc.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = lightColors(
    primary = WhiteBackground1,
    secondary = WhiteBackground2,
    onPrimary = BlackText
)

private val LightColorPalette = darkColors(
    primary = BlackBackground1,
    onPrimary = WhiteText,
    secondary = BlackBackground2,
)

@Composable
fun SmartCalcTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = SmartCalcTYpography,
        shapes = Shapes,
        content = content
    )
}