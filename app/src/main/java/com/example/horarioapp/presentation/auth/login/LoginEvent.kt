package com.example.horarioapp.presentation.auth.login

sealed class LoginEvent {
    data class OnEmailChange(val email: String) : LoginEvent()
    data class OnPasswordChange(val password: String) : LoginEvent()
    data object OnLoginClick : LoginEvent()
    data object OnDismissError : LoginEvent()
}
