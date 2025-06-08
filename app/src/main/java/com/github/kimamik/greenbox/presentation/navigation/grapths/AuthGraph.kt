package com.github.kimamik.greenbox.presentation.navigation.grapths

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.kimamik.greenbox.presentation.auth.login.LoginScreenRoot
import com.github.kimamik.greenbox.presentation.navigation.Routs.Auth

fun NavGraphBuilder.authGraph() {
    navigation<Auth>(startDestination = Auth.Login) {
        composable<Auth.Login> {
            LoginScreenRoot()
        }
    }
}