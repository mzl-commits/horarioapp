package com.example.horarioapp.presentation.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horarioapp.domain.repository.AuthRepository
import com.example.horarioapp.domain.repository.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CalendarState(selectedDay = currentDayIndex()))
    val state: StateFlow<CalendarState> = _state.asStateFlow()

    private var schedulesJob: Job? = null

    init {
        viewModelScope.launch {
            authRepository.getCurrentUser().collect { user ->
                schedulesJob?.cancel()
                if (user == null) {
                    _state.value = _state.value.copy(
                        userId = "",
                        schedules = emptyList(),
                        isLoading = false
                    )
                } else {
                    _state.value = _state.value.copy(userId = user.uid, isLoading = true)
                    schedulesJob = viewModelScope.launch {
                        scheduleRepository.getSchedules(user.uid).collect { schedules ->
                            _state.value = _state.value.copy(
                                schedules = schedules,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                }
            }
        }
    }

    fun selectDay(day: Int) {
        _state.value = _state.value.copy(selectedDay = day)
    }

    private fun currentDayIndex(): Int {
        return when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> 0
            Calendar.TUESDAY -> 1
            Calendar.WEDNESDAY -> 2
            Calendar.THURSDAY -> 3
            Calendar.FRIDAY -> 4
            Calendar.SATURDAY -> 5
            else -> 6
        }
    }
}
