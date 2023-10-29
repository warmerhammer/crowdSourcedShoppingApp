package com.warmerhammer.crowdsourceshoppingapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFF73A561),
    primaryVariant = Color(0xFF7F5BBC),
    secondary = Color(0xFF9361A5),
    onSurface = Color.Black,
    background = Color(0xFFEBEEE8),
    onBackground = Color.Black,
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF73A561),
    primaryVariant = Color(0xFF7F5BBC),
    secondary = Color(0xFF9361A5),
    onSurface = Color.Black,
    background = Color(0xFFEBEEE8),
    onBackground = Color.Black,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CrowdSourceShoppingAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}