package com.github.kimamik.greenbox.presentation.navigation

import kotlinx.serialization.Serializable

interface Rout {
    @Serializable
    object Onboarding : Rout

    @Serializable
    object Auth : Rout {
        @Serializable
        object Login : Rout
    }

    @Serializable
    object Main : Rout {
        @Serializable
        object Courses : Rout

        @Serializable
        object Favorites : Rout

        @Serializable
        object Profile : Rout
    }
}