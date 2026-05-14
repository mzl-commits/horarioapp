package com.example.horarioapp.domain.usecase.auth


import com.example.horarioapp.domain.model.User
import com.example.horarioapp.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        confirmPassword: String,
        fullName: String
    ): Result<User> {
        // Validación de email
        if (email.isBlank() || !email.contains("@")) {
            return Result.failure(
                IllegalArgumentException("Email inválido")
            )
        }

        // Validación de nombre
        if (fullName.isBlank() || fullName.length < 3) {
            return Result.failure(
                IllegalArgumentException("El nombre debe tener al menos 3 caracteres")
            )
        }

        // Validación de contraseña
        if (password.isBlank() || password.length < 6) {
            return Result.failure(
                IllegalArgumentException("Contraseña debe tener al menos 6 caracteres")
            )
        }

        // Validación de confirmación
        if (password != confirmPassword) {
            return Result.failure(
                IllegalArgumentException("Las contraseñas no coinciden")
            )
        }

        return authRepository.register(email, password, fullName)
    }
}