package com.example.horarioapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horarioapp.domain.model.Schedule
import com.example.horarioapp.domain.model.User
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
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private var schedulesJob: Job? = null

    init {
        viewModelScope.launch {
            authRepository.getCurrentUser().collect { user ->
                schedulesJob?.cancel()
                _state.value = _state.value.copy(user = user)
                if (user != null) {
                    schedulesJob = viewModelScope.launch {
                        scheduleRepository.getSchedules(user.uid).collect { schedules ->
                            _state.value = _state.value.copy(schedules = schedules)
                        }
                    }
                } else {
                    _state.value = _state.value.copy(schedules = emptyList())
                }
            }
        }
    }
}

data class HomeState(
    val user: User? = null,
    val schedules: List<Schedule> = emptyList()
) {
    private val todayIndex: Int
        get() = when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> 0
            Calendar.TUESDAY -> 1
            Calendar.WEDNESDAY -> 2
            Calendar.THURSDAY -> 3
            Calendar.FRIDAY -> 4
            Calendar.SATURDAY -> 5
            else -> 6
        }

    val todaySchedules: List<Schedule>
        get() = schedules.filter { it.dayOfWeek == todayIndex }.sortedBy { it.startTime }
}
