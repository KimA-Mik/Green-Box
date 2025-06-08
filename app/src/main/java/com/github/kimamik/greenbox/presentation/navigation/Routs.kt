package com.github.kimamik.greenbox.presentation.navigation

import kotlinx.serialization.Serializable

object Routs {
    @Serializable
    object Onboarding

    @Serializable
    object Auth {
        @Serializable
        object Login
    }

    @Serializable
    object Main {
        @Serializable
        object Courses
    }
}