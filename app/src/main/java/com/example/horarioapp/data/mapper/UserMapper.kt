package com.example.horarioapp.data.mapper


import com.example.horarioapp.data.local.entity.UserEntity
import com.example.horarioapp.domain.model.User

fun UserEntity.toUser(): User {
    return User(
        uid = uid,
        email = email,
        fullName = fullName,
        universityCareer = universityCareer,
        semester = semester,
        profilePictureUrl = profilePictureUrl
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        uid = uid,
        email = email,
        fullName = fullName,
        universityCareer = universityCareer,
        semester = semester,
        profilePictureUrl = profilePictureUrl
    )
}