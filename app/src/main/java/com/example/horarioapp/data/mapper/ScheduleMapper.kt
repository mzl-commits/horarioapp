package com.example.horarioapp.data.mapper


import com.example.horarioapp.data.local.entity.ScheduleEntity
import com.example.horarioapp.domain.model.Schedule

fun ScheduleEntity.toSchedule(): Schedule {
    return Schedule(
        id = id,
        subject = subject,
        professor = professor,
        classroom = classroom,
        dayOfWeek = dayOfWeek,
        startTime = startTime,
        endTime = endTime,
        color = color,
        userId = userId
    )
}

fun Schedule.toScheduleEntity(): ScheduleEntity {
    return ScheduleEntity(
        id = id,
        subject = subject,
        professor = professor,
        classroom = classroom,
        dayOfWeek = dayOfWeek,
        startTime = startTime,
        endTime = endTime,
        color = color,
        userId = userId
    )
}

fun List<ScheduleEntity>.toSchedules(): List<Schedule> {
    return map { it.toSchedule() }
}

fun List<Schedule>.toScheduleEntities(): List<ScheduleEntity> {
    return map { it.toScheduleEntity() }
}