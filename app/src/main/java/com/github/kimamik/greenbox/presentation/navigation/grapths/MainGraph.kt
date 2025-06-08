package com.github.kimamik.greenbox.presentation.navigation.grapths

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.kimamik.greenbox.presentation.courses.main.CoursesScreenRoot
import com.github.kimamik.greenbox.presentation.navigation.Routs.Main

fun NavGraphBuilder.mainGraph() {
    navigation<Main>(startDestination = Main.Courses) {
        composable<Main.Courses> {
            CoursesScreenRoot()
        }
    }
}

fun NavController.enterMainGraph() = navigate(Main)