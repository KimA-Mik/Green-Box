package com.github.kimamik.greenbox.presentation.navigation.grapths

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.kimamik.greenbox.presentation.auth.login.LoginScreenRoot

private const val LOGIN_DESTINATION = "login"
const val AUTH_GRAPH = "auth"
fun NavGraphBuilder.authGraph(
    navHostController: NavHostController
) {
    navigation(startDestination = LOGIN_DESTINATION, route = AUTH_GRAPH) {
        composable(LOGIN_DESTINATION) {
            LoginScreenRoot()
        }
    }
}