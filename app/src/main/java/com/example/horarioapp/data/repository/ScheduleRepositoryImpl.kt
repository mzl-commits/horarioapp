package com.example.horarioapp.data.repository


import com.google.firebase.firestore.FirebaseFirestore
import com.example.horarioapp.data.local.dao.ScheduleDao
import com.example.horarioapp.data.mapper.toSchedule
import com.example.horarioapp.data.mapper.toScheduleEntity
import com.example.horarioapp.data.mapper.toSchedules
import com.example.horarioapp.data.remote.dto.ScheduleDto
import com.example.horarioapp.domain.model.Schedule
import com.example.horarioapp.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val scheduleDao: ScheduleDao
) : ScheduleRepository {

    override fun getSchedules(userId: String): Flow<List<Schedule>> {
        return scheduleDao.getAllSchedulesForUser(userId).map { entities ->
            entities.toSchedules()
        }
    }

    override fun getSchedulesByDay(userId: String, dayOfWeek: Int): Flow<List<Schedule>> {
        return scheduleDao.getSchedulesForDay(userId, dayOfWeek).map { entities ->
            entities.toSchedules()
        }
    }

    override suspend fun getScheduleById(scheduleId: String): Result<Schedule> {
        return try {
            val entity = scheduleDao.getScheduleById(scheduleId)
                ?: return Result.failure(Exception("Horario no encontrado"))
            Result.success(entity.toSchedule())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addSchedule(schedule: Schedule): Result<String> {
        return try {
            val scheduleEntity = schedule.toScheduleEntity()
            scheduleDao.insertSchedule(scheduleEntity)

            // Guardar en Firebase de forma asíncrona (sin bloquear)
            try {
                firestore.collection("schedules").document(schedule.id).set(
                    mapOf(
                        "id" to schedule.id,
                        "subject" to schedule.subject,
                        "professor" to schedule.professor,
                        "classroom" to schedule.classroom,
                        "dayOfWeek" to schedule.dayOfWeek,
                        "startTime" to schedule.startTime,
                        "endTime" to schedule.endTime,
                        "color" to schedule.color,
                        "userId" to schedule.userId,
                        "createdAt" to System.currentTimeMillis(),
                        "updatedAt" to System.currentTimeMillis()
                    )
                ).await()

                // Marcar como sincronizado
                scheduleDao.markAsSynced(schedule.id)
            } catch (e: Exception) {
                // Si falla Firebase, quedará guardado en Room y se sincronizará después
                println("Error sincronizando con Firebase: ${e.message}")
            }

            Result.success(schedule.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateSchedule(schedule: Schedule): Result<Unit> {
        return try {
            val scheduleEntity = schedule.toScheduleEntity().copy(isSyncedWithServer = false)
            scheduleDao.updateSchedule(scheduleEntity)

            // Actualizar en Firebase
            try {
                firestore.collection("schedules").document(schedule.id).set(
                    mapOf(
                        "id" to schedule.id,
                        "subject" to schedule.subject,
                        "professor" to schedule.professor,
                        "classroom" to schedule.classroom,
                        "dayOfWeek" to schedule.dayOfWeek,
                        "startTime" to schedule.startTime,
                        "endTime" to schedule.endTime,
                        "color" to schedule.color,
                        "userId" to schedule.userId,
                        "updatedAt" to System.currentTimeMillis()
                    ),
                    com.google.firebase.firestore.SetOptions.merge()
                ).await()

                scheduleDao.markAsSynced(schedule.id)
            } catch (e: Exception) {
                println("Error sincronizando con Firebase: ${e.message}")
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteSchedule(scheduleId: String): Result<Unit> {
        return try {
            scheduleDao.deleteScheduleById(scheduleId)

            // Eliminar de Firebase
            try {
                firestore.collection("schedules").document(scheduleId).delete().await()
            } catch (e: Exception) {
                println("Error eliminando de Firebase: ${e.message}")
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun syncSchedules(userId: String): Result<Unit> {
        return try {
            // Descargar horarios desde Firebase
            val snapshot = firestore.collection("schedules")
                .whereEqualTo("userId", userId)
                .get()
                .await()

            val schedules = snapshot.toObjects(ScheduleDto::class.java)
                .map { it.toScheduleEntity() }

            // Guardar en Room
            scheduleDao.insertSchedules(schedules)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUnsyncedSchedules(userId: String): Result<List<Schedule>> {
        return try {
            val entities = scheduleDao.getUnsyncedSchedules(userId)
            Result.success(entities.toSchedules())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}