package com.example.horarioapp.presentation.calendar

import com.example.horarioapp.domain.model.Schedule

data class CalendarState(
    val userId: String = "",
    val selectedDay: Int = 0,
    val schedules: List<Schedule> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
) {
    val schedulesForSelectedDay: List<Schedule>
        get() = schedules
            .filter { it.dayOfWeek == selectedDay }
            .sortedBy { it.startTime }
}
