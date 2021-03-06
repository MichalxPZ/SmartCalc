package com.poznan.put.michalxpz.smartcalc.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.poznan.put.michalxpz.core_ui.Dimensions
import com.poznan.put.michalxpz.core_ui.LocalSpacing

private val LightColorPalette = lightColors(
    primary = WhiteBackground1,
    secondary = WhiteBackground2,
    onPrimary = BlackText
)

private val DarkColorPalette = darkColors(
    primary = BlackBackground1,
    onPrimary = WhiteText,
    secondary = BlackBackground2,
)

@Composable
fun SmartCalcTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = DarkColorPalette

    CompositionLocalProvider(LocalSpacing provides Dimensions()) {
        MaterialTheme(
            colors = colors,
            typography = SmartCalcTYpography,
            shapes = Shapes,
            content = content
        )
    }
}