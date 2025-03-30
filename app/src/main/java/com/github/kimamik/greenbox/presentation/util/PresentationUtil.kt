package com.github.kimamik.greenbox.presentation.util

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.github.kimamik.greenbox.ui.theme.GreenBoxTheme


@Composable
fun GBPreview(
    content: @Composable () -> Unit
) {
    GreenBoxTheme {
        CompositionLocalProvider(
            LocalNavController provides rememberNavController()
        ) {
            Surface {
                content()
            }
        }
    }
}