package com.github.kimamik.greenbox.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.kimamik.greenbox.presentation.navigation.grapths.authGraph
import com.github.kimamik.greenbox.presentation.navigation.grapths.mainGraph
import com.github.kimamik.greenbox.presentation.navigation.grapths.onboarding

@Composable
fun GBNavHost(
    showOnboarding: Boolean,
    navController: NavHostController,
    acceptOnboarding: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = if (showOnboarding) Routs.Onboarding else Routs.Auth
    ) {
        onboarding(acceptOnboarding)
        authGraph()
        mainGraph()
    }
}