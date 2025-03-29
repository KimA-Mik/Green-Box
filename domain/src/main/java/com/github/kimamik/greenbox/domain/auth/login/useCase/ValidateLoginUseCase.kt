package com.github.kimamik.greenbox.domain.auth.login.useCase

import javax.inject.Inject

class ValidateLoginUseCase @Inject constructor() {
    private val validator = """^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]+$""".toRegex()
    operator fun invoke(login: String, password: String): Boolean {
        if (password.isEmpty()) return false
        if (!validator.matches(login)) return false
        return true
    }
}