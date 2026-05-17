package com.example.horarioapp.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horarioapp.domain.usecase.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnFullNameChange -> {
                _state.value = _state.value.copy(fullName = event.fullName, error = null)
            }
            is RegisterEvent.OnEmailChange -> {
                _state.value = _state.value.copy(email = event.email, error = null)
            }
            is RegisterEvent.OnPasswordChange -> {
                _state.value = _state.value.copy(password = event.password, error = null)
            }
            is RegisterEvent.OnConfirmPasswordChange -> {
                _state.value = _state.value.copy(confirmPassword = event.confirmPassword, error = null)
            }
            RegisterEvent.OnRegisterClick -> register()
        }
    }

    private fun register() {
        val currentState = _state.value
        if (currentState.isLoading) return

        _state.value = currentState.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = registerUseCase(
                email = currentState.email.trim(),
                password = currentState.password,
                confirmPassword = currentState.confirmPassword,
                fullName = currentState.fullName.trim()
            )

            result.onSuccess {
                _state.value = _state.value.copy(isLoading = false, isRegistered = true)
            }.onFailure { exception ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = exception.message ?: "No se pudo crear la cuenta"
                )
            }
        }
    }
}
