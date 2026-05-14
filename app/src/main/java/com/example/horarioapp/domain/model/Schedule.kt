package com.example.horarioapp.domain.model

data class Schedule(
    val id: String,
    val subject: String,
    val professor: String,
    val classroom: String,
    val dayOfWeek: Int, // 0 = Lunes, 1 = Martes, ..., 6 = Domingo
    val startTime: String, // formato HH:mm
    val endTime: String, // formato HH:mm
    val color: String, // código hex
    val userId: String
) {
    // Propiedad calculada para obtener el nombre del día
    val dayName: String
        get() = when (dayOfWeek) {
            0 -> "Lunes"
            1 -> "Martes"
            2 -> "Miércoles"
            3 -> "Jueves"
            4 -> "Viernes"
            5 -> "Sábado"
            6 -> "Domingo"
            else -> "Desconocido"
        }

    // Validar que los tiempos sean válidos
    fun isValid(): Boolean {
        return subject.isNotBlank() &&
                professor.isNotBlank() &&
                classroom.isNotBlank() &&
                startTime.matches(Regex("\\d{2}:\\d{2}")) &&
                endTime.matches(Regex("\\d{2}:\\d{2}"))
    }
}