package com.example.horarioapp.domain.repository


import com.example.horarioapp.domain.model.Schedule
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {

    // Obtener todos los horarios del usuario
    fun getSchedules(userId: String): Flow<List<Schedule>>

    // Obtener horarios de un día específico
    fun getSchedulesByDay(userId: String, dayOfWeek: Int): Flow<List<Schedule>>

    // Obtener un horario por ID
    suspend fun getScheduleById(scheduleId: String): Result<Schedule>

    // Agregar nuevo horario
    suspend fun addSchedule(schedule: Schedule): Result<String>

    // Actualizar horario existente
    suspend fun updateSchedule(schedule: Schedule): Result<Unit>

    // Eliminar horario
    suspend fun deleteSchedule(scheduleId: String): Result<Unit>

    // Sincronizar con Firebase (descargar cambios)
    suspend fun syncSchedules(userId: String): Result<Unit>

    // Obtener horarios no sincronizados
    suspend fun getUnsyncedSchedules(userId: String): Result<List<Schedule>>
}