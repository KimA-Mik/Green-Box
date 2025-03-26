package com.github.kimamik.greenbox.presentation.util

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.github.kimamik.greenbox.ui.theme.GreenBoxTheme


@Composable
fun GBPreview(
    content: @Composable () -> Unit
) {
    GreenBoxTheme {
        Surface {
            content()
        }
    }
}