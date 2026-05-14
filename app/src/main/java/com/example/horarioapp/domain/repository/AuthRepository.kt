package com.example.horarioapp.domain.repository


import com.example.horarioapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    // Obtener usuario actual
    fun getCurrentUser(): Flow<User?>

    // Login
    suspend fun login(email: String, password: String): Result<User>

    // Registro
    suspend fun register(
        email: String,
        password: String,
        fullName: String
    ): Result<User>

    // Logout
    suspend fun logout(): Result<Unit>

    // Verificar si está autenticado
    suspend fun isAuthenticated(): Boolean

    // Actualizar perfil
    suspend fun updateProfile(user: User): Result<Unit>
}