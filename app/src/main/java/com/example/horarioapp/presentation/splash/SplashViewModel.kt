package com.example.horarioapp.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horarioapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<SplashState>(SplashState.Loading)
    val state: StateFlow<SplashState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val localUser = authRepository.getCurrentUser().first()
            _state.value = if (localUser != null || authRepository.isAuthenticated()) {
                SplashState.Authenticated
            } else {
                SplashState.Unauthenticated
            }
        }
    }
}

sealed class SplashState {
    data object Loading : SplashState()
    data object Authenticated : SplashState()
    data object Unauthenticated : SplashState()
}
