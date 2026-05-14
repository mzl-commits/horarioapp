package com.example.horarioapp.data.remote.dto


import com.google.firebase.firestore.PropertyName
import com.example.horarioapp.data.local.entity.UserEntity

data class UserDto(
    @PropertyName("uid")
    val uid: String = "",

    @PropertyName("email")
    val email: String = "",

    @PropertyName("fullName")
    val fullName: String = "",

    @PropertyName("universityCareer")
    val universityCareer: String = "",

    @PropertyName("semester")
    val semester: Int = 0,

    @PropertyName("profilePictureUrl")
    val profilePictureUrl: String = "",

    @PropertyName("createdAt")
    val createdAt: Long = System.currentTimeMillis(),

    @PropertyName("updatedAt")
    val updatedAt: Long = System.currentTimeMillis()
) {
    // Método para convertir a entidad Room
    fun toUserEntity(): UserEntity {
        return UserEntity(
            uid = uid,
            email = email,
            fullName = fullName,
            universityCareer = universityCareer,
            semester = semester,
            profilePictureUrl = profilePictureUrl,
            createdAt = createdAt,
            updatedAt = updatedAt,
            lastSyncedAt = System.currentTimeMillis()
        )
    }
}