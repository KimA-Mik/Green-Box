package com.github.kimamik.greenbox.presentation.util

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import dev.chrisbanes.haze.HazeState

val LocalNavController = compositionLocalOf<NavController> {
    error("CompositionLocal LocalNavController not presented")
}

val LocalHazeState = compositionLocalOf<HazeState> {
    HazeState()
}

