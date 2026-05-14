package com.example.horarioapp.data.local.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.horarioapp.data.local.entity.ScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    // INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: ScheduleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedules(schedules: List<ScheduleEntity>)

    // UPDATE
    @Update
    suspend fun updateSchedule(schedule: ScheduleEntity)

    // DELETE
    @Delete
    suspend fun deleteSchedule(schedule: ScheduleEntity)

    @Query("DELETE FROM schedules WHERE id = :scheduleId")
    suspend fun deleteScheduleById(scheduleId: String)

    @Query("DELETE FROM schedules WHERE userId = :userId")
    suspend fun deleteAllSchedulesForUser(userId: String)

    // SELECT
    @Query("SELECT * FROM schedules WHERE id = :scheduleId LIMIT 1")
    suspend fun getScheduleById(scheduleId: String): ScheduleEntity?

    // Obtener todos los horarios de un usuario
    @Query("SELECT * FROM schedules WHERE userId = :userId ORDER BY dayOfWeek ASC, startTime ASC")
    fun getAllSchedulesForUser(userId: String): Flow<List<ScheduleEntity>>

    // Obtener horarios de un día específico
    @Query("SELECT * FROM schedules WHERE userId = :userId AND dayOfWeek = :dayOfWeek ORDER BY startTime ASC")
    fun getSchedulesForDay(userId: String, dayOfWeek: Int): Flow<List<ScheduleEntity>>

    // Obtener horarios de hoy (necesita la lógica de qué es "hoy")
    @Query("SELECT * FROM schedules WHERE userId = :userId ORDER BY startTime ASC")
    fun getTodaySchedules(userId: String): Flow<List<ScheduleEntity>>

    // Obtener horarios no sincronizados (cambios locales pendientes)
    @Query("SELECT * FROM schedules WHERE userId = :userId AND isSyncedWithServer = 0")
    suspend fun getUnsyncedSchedules(userId: String): List<ScheduleEntity>

    // Marcar como sincronizado
    @Query("UPDATE schedules SET isSyncedWithServer = 1 WHERE id = :scheduleId")
    suspend fun markAsSynced(scheduleId: String)

    // Contar horarios
    @Query("SELECT COUNT(*) FROM schedules WHERE userId = :userId")
    suspend fun countSchedules(userId: String): Int
}