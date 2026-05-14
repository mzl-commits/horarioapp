package com.example.horarioapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val uid: String,
    val email: String,
    val fullName: String,
    val universityCareer: String = "", // Carrera (ej: Ingeniería de Sistemas)
    val semester: Int = 0, // Semestre/Ciclo
    val profilePictureUrl: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val lastSyncedAt: Long = 0 // última sincronización con Firebase
)