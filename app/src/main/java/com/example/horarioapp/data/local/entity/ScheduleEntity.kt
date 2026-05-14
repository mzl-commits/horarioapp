package com.example.horarioapp.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "schedules")
data class ScheduleEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val subject: String,
    val professor: String,
    val classroom: String,
    val dayOfWeek: Int, // 0 = Lunes, 1 = Martes, ..., 6 = Domingo
    val startTime: String, // formato HH:mm (ej: 08:00)
    val endTime: String, // formato HH:mm (ej: 10:00)
    val color: String, // código hex del color (ej: #FF6969)
    val userId: String, // relación con el usuario propietario
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val isSyncedWithServer: Boolean = false // para saber si está en Firebase
)