package com.github.kimamik.greenbox.presentation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.github.kimamik.greenbox.presentation.navigation.GBNavHost
import com.github.kimamik.greenbox.presentation.util.LocalNavController
import com.github.kimamik.greenbox.ui.theme.GreenBoxTheme

@Composable
fun ApplicationScreen(
    showOnboarding: Boolean = false,
    acceptOnboarding: () -> Unit
) = GreenBoxTheme {

    val navController = rememberNavController()
    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        Surface {
            GBNavHost(
                navController = navController,
                showOnboarding = showOnboarding,
                acceptOnboarding = {
//                    navController.popBackStack()
//                    navController.navigate(Routs.Auth)
//                    navController.
                    acceptOnboarding()
                }
            )
        }
    }
}
