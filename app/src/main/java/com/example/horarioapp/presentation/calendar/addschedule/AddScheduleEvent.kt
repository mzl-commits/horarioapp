package com.example.horarioapp.presentation.calendar.addschedule


sealed class AddScheduleEvent {
    data class OnSubjectChange(val subject: String) : AddScheduleEvent()
    data class OnProfessorChange(val professor: String) : AddScheduleEvent()
    data class OnClassroomChange(val classroom: String) : AddScheduleEvent()
    data class OnDaySelected(val day: Int) : AddScheduleEvent()
    data class OnStartTimeChange(val time: String) : AddScheduleEvent()
    data class OnEndTimeChange(val time: String) : AddScheduleEvent()
    data class OnColorSelected(val color: String) : AddScheduleEvent()
    object OnAddSchedule : AddScheduleEvent()
    object OnDismissError : AddScheduleEvent()
    object OnDismissSuccess : AddScheduleEvent()
    object OnToggleColorPicker : AddScheduleEvent()
    object OnToggleDayPicker : AddScheduleEvent()
    object OnToggleStartTimePicker : AddScheduleEvent()
    object OnToggleEndTimePicker : AddScheduleEvent()
}
