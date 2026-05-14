package com.example.horarioapp.domain.usecase.schedule


import com.example.horarioapp.domain.repository.ScheduleRepository
import javax.inject.Inject

class DeleteScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {
    suspend operator fun invoke(scheduleId: String): Result<Unit> {
        if (scheduleId.isBlank()) {
            return Result.failure(
                IllegalArgumentException("scheduleId no puede estar vacío")
            )
        }
        return scheduleRepository.deleteSchedule(scheduleId)
    }
}