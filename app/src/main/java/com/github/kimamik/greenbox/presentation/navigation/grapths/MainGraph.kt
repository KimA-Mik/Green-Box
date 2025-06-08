package com.github.kimamik.greenbox.presentation.navigation.grapths

import androidx.compose.material3.Scaffold
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.kimamik.greenbox.presentation.courses.main.CoursesScreenRoot
import com.github.kimamik.greenbox.presentation.navigation.GBNavBar
import com.github.kimamik.greenbox.presentation.navigation.Rout.Main

fun NavGraphBuilder.mainGraph() {
    navigation<Main>(startDestination = Main.Courses) {
        composable<Main.Courses> {
            CoursesScreenRoot()
        }

        composable<Main.Favorites> {

        }

        composable<Main.Profile> {
            Scaffold(
                bottomBar = { GBNavBar() }
            ) {

            }
        }
    }
}

fun NavController.enterMainGraph() = navigate(Main)