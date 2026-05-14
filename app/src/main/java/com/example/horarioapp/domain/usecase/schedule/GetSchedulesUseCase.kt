package com.example.horarioapp.domain.usecase.schedule


import com.example.horarioapp.domain.model.Schedule
import com.example.horarioapp.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSchedulesUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {
    operator fun invoke(userId: String): Flow<List<Schedule>> {
        if (userId.isBlank()) {
            throw IllegalArgumentException("userId no puede estar vacío")
        }
        return scheduleRepository.getSchedules(userId)
    }
}