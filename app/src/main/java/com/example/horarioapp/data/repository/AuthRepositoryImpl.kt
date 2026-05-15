package com.example.horarioapp.data.repository


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.example.horarioapp.data.local.dao.UserDao
import com.example.horarioapp.data.mapper.toUser
import com.example.horarioapp.data.mapper.toUserEntity
import com.example.horarioapp.data.remote.dto.UserDto
import com.example.horarioapp.domain.model.User
import com.example.horarioapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth?,
    private val firestore: FirebaseFirestore?,
    private val userDao: UserDao
) : AuthRepository {

    override fun getCurrentUser(): Flow<User?> {
        return userDao.getCurrentUser().map { userEntity ->
            userEntity?.toUser()
        }
    }

    override suspend fun login(email: String, password: String): Result<User> {
        if (firebaseAuth == null || firestore == null) {
            return Result.failure(Exception("Firebase no está configurado (falta google-services.json)"))
        }
        return try {
            // Autenticar con Firebase
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user ?: return Result.failure(Exception("Usuario no encontrado"))

            // Obtener datos del usuario de Firestore
            val userSnapshot = firestore.collection("users").document(firebaseUser.uid).get().await()
            val userDto = userSnapshot.toObject(UserDto::class.java)
                ?: return Result.failure(Exception("Datos de usuario no encontrados"))

            // Guardar en Room
            val userEntity = userDto.toUserEntity()
            userDao.insertUser(userEntity)

            Result.success(userEntity.toUser())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        fullName: String
    ): Result<User> {
        if (firebaseAuth == null || firestore == null) {
            return Result.failure(Exception("Firebase no está configurado (falta google-services.json)"))
        }
        return try {
            // Crear usuario en Firebase Auth
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user ?: return Result.failure(Exception("Error creando usuario"))

            // Actualizar nombre en Firebase Auth
            val profileUpdate = userProfileChangeRequest {
                displayName = fullName
            }
            firebaseUser.updateProfile(profileUpdate).await()

            // Crear documento en Firestore
            val userDto = UserDto(
                uid = firebaseUser.uid,
                email = email,
                fullName = fullName
            )
            firestore.collection("users").document(firebaseUser.uid).set(userDto).await()

            // Guardar en Room
            val userEntity = userDto.toUserEntity()
            userDao.insertUser(userEntity)

            Result.success(userEntity.toUser())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Unit> {
        return try {
            firebaseAuth?.signOut()
            userDao.clearAllUsers()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isAuthenticated(): Boolean {
        return firebaseAuth?.currentUser != null
    }

    override suspend fun updateProfile(user: User): Result<Unit> {
        if (firebaseAuth == null || firestore == null) {
            return Result.failure(Exception("Firebase no está configurado (falta google-services.json)"))
        }
        return try {
            val currentUser = firebaseAuth.currentUser
                ?: return Result.failure(Exception("No hay usuario logueado"))

            // Actualizar en Firestore
            val userDto = UserDto(
                uid = user.uid,
                email = user.email,
                fullName = user.fullName,
                universityCareer = user.universityCareer,
                semester = user.semester,
                profilePictureUrl = user.profilePictureUrl
            )
            firestore.collection("users").document(user.uid).set(userDto).await()

            // Actualizar en Room
            val userEntity = userDto.toUserEntity()
            userDao.updateUser(userEntity)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}