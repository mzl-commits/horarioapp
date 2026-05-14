package com.example.horarioapp.domain.model


data class User(
    val uid: String,
    val email: String,
    val fullName: String,
    val universityCareer: String = "",
    val semester: Int = 0,
    val profilePictureUrl: String = ""
) {
    fun getInitials(): String {
        return fullName
            .split(" ")
            .take(2)
            .mapNotNull { it.firstOrNull() }
            .joinToString("")
            .uppercase()
    }

    fun isValid(): Boolean {
        return uid.isNotBlank() &&
                email.isNotBlank() &&
                fullName.isNotBlank() &&
                email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$"))
    }
}