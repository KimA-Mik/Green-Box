package com.github.kimamik.greenbox.presentation.auth.login

data class LoginScreenState(
    val login: String = "",
    val password: String = "",
    val validated: Boolean = false,
)
