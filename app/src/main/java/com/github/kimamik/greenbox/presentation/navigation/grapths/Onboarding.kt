package com.github.kimamik.greenbox.presentation.navigation.grapths

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

import com.github.kimamik.greenbox.presentation.navigation.Routs.Onboarding
import com.github.kimamik.greenbox.presentation.onboarding.OnboardingScreenRoot

fun NavGraphBuilder.onboarding(
    acceptOnboarding: () -> Unit
) {
    composable<Onboarding> {
        OnboardingScreenRoot(acceptOnboarding = acceptOnboarding)
    }
}