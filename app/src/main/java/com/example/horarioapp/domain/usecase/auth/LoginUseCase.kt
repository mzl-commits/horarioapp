package com.example.horarioapp.domain.usecase.auth


import com.example.horarioapp.domain.model.User
import com.example.horarioapp.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        // Validación de email
        if (email.isBlank() || !email.contains("@")) {
            return Result.failure(
                IllegalArgumentException("Email inválido")
            )
        }

        // Validación de contraseña
        if (password.isBlank() || password.length < 6) {
            return Result.failure(
                IllegalArgumentException("Contraseña debe tener al menos 6 caracteres")
            )
        }

        return authRepository.login(email, password)
    }
}