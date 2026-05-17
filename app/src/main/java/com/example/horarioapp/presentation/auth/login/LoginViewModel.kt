package com.example.horarioapp.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horarioapp.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChange -> {
                _state.value = _state.value.copy(email = event.email, error = null)
            }
            is LoginEvent.OnPasswordChange -> {
                _state.value = _state.value.copy(password = event.password, error = null)
            }
            LoginEvent.OnLoginClick -> login()
            LoginEvent.OnDismissError -> {
                _state.value = _state.value.copy(error = null)
            }
        }
    }

    private fun login() {
        val currentState = _state.value
        if (currentState.isLoading) return

        _state.value = currentState.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = loginUseCase(
                email = currentState.email.trim(),
                password = currentState.password
            )

            result.onSuccess {
                _state.value = _state.value.copy(isLoading = false, isLoggedIn = true)
            }.onFailure { exception ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = exception.message ?: "No se pudo iniciar sesión"
                )
            }
        }
    }
}
