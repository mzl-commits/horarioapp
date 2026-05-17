package com.example.horarioapp.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horarioapp.domain.model.User
import com.example.horarioapp.domain.usecase.auth.LogoutUseCase
import com.example.horarioapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            authRepository.getCurrentUser().collect { user ->
                _state.value = _state.value.copy(user = user)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            _state.value = _state.value.copy(isLoggedOut = true)
        }
    }
}

data class ProfileState(
    val user: User? = null,
    val isLoggedOut: Boolean = false
)
