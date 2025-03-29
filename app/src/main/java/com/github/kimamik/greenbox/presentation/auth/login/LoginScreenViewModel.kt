package com.github.kimamik.greenbox.presentation.auth.login

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@Stable
@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel() {
    private val login = MutableStateFlow("")
    private val password = MutableStateFlow("")
    private val isValid = MutableStateFlow(false)

    val state = combine(login, password, isValid) { login, password, isValid ->
        LoginScreenState(
            login = login,
            password = password,
            validated = isValid
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), LoginScreenState())

    fun onEmailChange(value: String) {
        login.value = value
    }

    fun onPasswordChange(value: String) {
        password.value = value
    }
}