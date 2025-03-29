package com.github.kimamik.greenbox.presentation.auth.login

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kimamik.greenbox.domain.auth.login.useCase.ValidateLoginUseCase
import com.github.kimamik.greenbox.presentation.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@Stable
@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val validateLogin: ValidateLoginUseCase
) : ViewModel() {
    private val _uiEvent = MutableStateFlow(Event<UiEvent>(null))
    val uiEvent = _uiEvent.asStateFlow()

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
        isValid.value = validateLogin(login.value, password.value)
    }

    fun onPasswordChange(value: String) {
        password.value = value
        isValid.value = validateLogin(login.value, password.value)
    }

    fun clickVk() {
        _uiEvent.value = Event(UiEvent.OpenVk)
    }

    fun clickOk() {
        _uiEvent.value = Event(UiEvent.OpenOk)
    }

    sealed interface UiEvent {
        data object OpenVk : UiEvent
        data object OpenOk : UiEvent
    }
}