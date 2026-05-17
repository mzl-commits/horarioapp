package com.example.horarioapp.presentation.calendar.addschedule


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horarioapp.domain.model.Schedule
import com.example.horarioapp.domain.repository.AuthRepository
import com.example.horarioapp.domain.usecase.schedule.AddScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddScheduleViewModel @Inject constructor(
    private val addScheduleUseCase: AddScheduleUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddScheduleState())
    val state: StateFlow<AddScheduleState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            authRepository.getCurrentUser().collect { user ->
                _state.value = _state.value.copy(userId = user?.uid.orEmpty())
            }
        }
    }

    fun onEvent(event: AddScheduleEvent) {
        when (event) {
            is AddScheduleEvent.OnSubjectChange -> {
                _state.value = _state.value.copy(
                    subject = event.subject,
                    subjectError = null
                )
            }
            is AddScheduleEvent.OnProfessorChange -> {
                _state.value = _state.value.copy(
                    professor = event.professor,
                    professorError = null
                )
            }
            is AddScheduleEvent.OnClassroomChange -> {
                _state.value = _state.value.copy(
                    classroom = event.classroom,
                    classroomError = null
                )
            }
            is AddScheduleEvent.OnDaySelected -> {
                _state.value = _state.value.copy(
                    selectedDay = event.day,
                    showDayPicker = false
                )
            }
            is AddScheduleEvent.OnStartTimeChange -> {
                _state.value = _state.value.copy(
                    startTime = event.time,
                    showStartTimePicker = false
                )
            }
            is AddScheduleEvent.OnEndTimeChange -> {
                _state.value = _state.value.copy(
                    endTime = event.time,
                    showEndTimePicker = false
                )
            }
            is AddScheduleEvent.OnColorSelected -> {
                _state.value = _state.value.copy(
                    selectedColor = event.color,
                    showColorPicker = false
                )
            }
            AddScheduleEvent.OnAddSchedule -> {
                addSchedule()
            }
            is AddScheduleEvent.OnDismissError -> {
                _state.value = _state.value.copy(error = null)
            }
            is AddScheduleEvent.OnDismissSuccess -> {
                _state.value = _state.value.copy(successMessage = null)
            }
            is AddScheduleEvent.OnToggleColorPicker -> {
                _state.value = _state.value.copy(
                    showColorPicker = !_state.value.showColorPicker
                )
            }
            is AddScheduleEvent.OnToggleDayPicker -> {
                _state.value = _state.value.copy(
                    showDayPicker = !_state.value.showDayPicker
                )
            }
            is AddScheduleEvent.OnToggleStartTimePicker -> {
                _state.value = _state.value.copy(
                    showStartTimePicker = !_state.value.showStartTimePicker
                )
            }
            is AddScheduleEvent.OnToggleEndTimePicker -> {
                _state.value = _state.value.copy(
                    showEndTimePicker = !_state.value.showEndTimePicker
                )
            }
        }
    }

    private fun addSchedule() {
        val currentState = _state.value

        // Validación
        var hasError = false
        var newState = currentState

        if (currentState.subject.isBlank()) {
            newState = newState.copy(subjectError = "La materia es requerida")
            hasError = true
        }
        if (currentState.professor.isBlank()) {
            newState = newState.copy(professorError = "El profesor es requerido")
            hasError = true
        }
        if (currentState.classroom.isBlank()) {
            newState = newState.copy(classroomError = "El aula es requerida")
            hasError = true
        }
        if (currentState.userId.isBlank()) {
            newState = newState.copy(error = "No hay usuario activo para guardar el horario")
            hasError = true
        }

        if (hasError) {
            _state.value = newState
            return
        }

        _state.value = _state.value.copy(isLoading = true)

        viewModelScope.launch {
            val schedule = Schedule(
                id = UUID.randomUUID().toString(),
                subject = currentState.subject,
                professor = currentState.professor,
                classroom = currentState.classroom,
                dayOfWeek = currentState.selectedDay,
                startTime = currentState.startTime,
                endTime = currentState.endTime,
                color = currentState.selectedColor,
                userId = currentState.userId
            )

            val result = addScheduleUseCase(schedule)

            result.onSuccess {
                _state.value = _state.value.copy(
                    isLoading = false,
                    successMessage = "Horario agregado correctamente"
                )
                // Reset form después de éxito
            }.onFailure { exception ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = exception.message ?: "Error al agregar horario"
                )
            }
        }
    }
}
