package com.example.horarioapp.presentation.calendar.addschedule

data class AddScheduleState(
    val subject: String = "",
    val professor: String = "",
    val classroom: String = "",
    val selectedDay: Int = 0,
    val startTime: String = "",
    val endTime: String = "",
    val selectedColor: String = "#FF6969",
    
    val subjectError: String? = null,
    val professorError: String? = null,
    val classroomError: String? = null,
    
    val showDayPicker: Boolean = false,
    val showStartTimePicker: Boolean = false,
    val showEndTimePicker: Boolean = false,
    val showColorPicker: Boolean = false,
    
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val error: String? = null
)

val DAYS_OF_WEEK = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
val AVAILABLE_COLORS = listOf("#FF6969", "#42A5F5", "#66BB6A", "#AB47BC", "#FFA726", "#26C6DA")