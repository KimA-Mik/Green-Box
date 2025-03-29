package com.github.kimamik.greenbox.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable


private val GreenBoxColorScheme = darkColorScheme(
    primary = Green,
    onPrimary = White,
    background = Dark,
    surfaceContainerHighest = LightGray,
)

@Composable
fun GreenBoxTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = GreenBoxColorScheme,
        typography = Typography,
        content = content
    )
}