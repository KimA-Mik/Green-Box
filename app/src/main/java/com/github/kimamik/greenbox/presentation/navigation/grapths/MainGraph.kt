package com.github.kimamik.greenbox.presentation.navigation.grapths

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.kimamik.greenbox.presentation.courses.main.CoursesScreenRoot
import com.github.kimamik.greenbox.presentation.navigation.NavItem

private const val COURSES_DESTINATION = "courses"
fun NavGraphBuilder.mainGraph(
    navController: NavHostController
) {
    navigation(startDestination = COURSES_DESTINATION, route = NavItem.Main.root) {
        composable(COURSES_DESTINATION) {
            CoursesScreenRoot()
        }
    }
}

fun NavController.enterMainGraph() = navigate(NavItem.entries.first().root)