package com.example.horarioapp.data.remote.dto


import com.google.firebase.firestore.PropertyName
import com.example.horarioapp.data.local.entity.ScheduleEntity

data class ScheduleDto(
    @PropertyName("id")
    val id: String = "",

    @PropertyName("subject")
    val subject: String = "",

    @PropertyName("professor")
    val professor: String = "",

    @PropertyName("classroom")
    val classroom: String = "",

    @PropertyName("dayOfWeek")
    val dayOfWeek: Int = 0,

    @PropertyName("startTime")
    val startTime: String = "",

    @PropertyName("endTime")
    val endTime: String = "",

    @PropertyName("color")
    val color: String = "#FF6969",

    @PropertyName("userId")
    val userId: String = "",

    @PropertyName("createdAt")
    val createdAt: Long = System.currentTimeMillis(),

    @PropertyName("updatedAt")
    val updatedAt: Long = System.currentTimeMillis()
) {
    // Método para convertir a entidad Room
    fun toScheduleEntity(): ScheduleEntity {
        return ScheduleEntity(
            id = id,
            subject = subject,
            professor = professor,
            classroom = classroom,
            dayOfWeek = dayOfWeek,
            startTime = startTime,
            endTime = endTime,
            color = color,
            userId = userId,
            createdAt = createdAt,
            updatedAt = updatedAt,
            isSyncedWithServer = true // viene de Firebase, así que está sincronizado
        )
    }
}