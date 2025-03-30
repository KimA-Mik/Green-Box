package com.github.kimamik.greenbox.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.kimamik.greenbox.presentation.navigation.grapths.AUTH_GRAPH
import com.github.kimamik.greenbox.presentation.navigation.grapths.authGraph
import com.github.kimamik.greenbox.presentation.navigation.grapths.mainGraph

@Composable
fun GBNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = AUTH_GRAPH) {
        authGraph(navController)
        mainGraph(navController)
    }
}