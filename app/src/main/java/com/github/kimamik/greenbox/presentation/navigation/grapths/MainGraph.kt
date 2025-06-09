package com.github.kimamik.greenbox.presentation.navigation.grapths

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.kimamik.greenbox.presentation.courses.favorite.FavoriteCoursesScreenRoot
import com.github.kimamik.greenbox.presentation.courses.main.CoursesScreenRoot
import com.github.kimamik.greenbox.presentation.navigation.GBNavBar
import com.github.kimamik.greenbox.presentation.navigation.Rout.Main

fun NavGraphBuilder.mainGraph() {
    navigation<Main>(startDestination = Main.Courses) {
        composable<Main.Courses> {
            CoursesScreenRoot()
        }

        composable<Main.Favorites> {
            FavoriteCoursesScreenRoot()
        }

        composable<Main.Profile> {
            Scaffold(
                bottomBar = { GBNavBar() }
            ) {
                Box(Modifier.padding(it))
            }
        }
    }
}

fun NavController.enterMainGraph() = navigate(Main)