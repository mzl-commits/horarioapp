package com.example.horarioapp.domain.usecase.schedule



import com.example.horarioapp.domain.model.Schedule
import com.example.horarioapp.domain.repository.ScheduleRepository
import javax.inject.Inject

class AddScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {
    suspend operator fun invoke(schedule: Schedule): Result<String> {
        // Validación básica
        if (!schedule.isValid()) {
            return Result.failure(
                IllegalArgumentException("El horario contiene datos inválidos")
            )
        }

        // Validar que el horario no sea negativo
        if (schedule.startTime >= schedule.endTime) {
            return Result.failure(
                IllegalArgumentException("La hora de fin debe ser mayor que la hora de inicio")
            )
        }

        // Delegar al repositorio
        return scheduleRepository.addSchedule(schedule)
    }
}