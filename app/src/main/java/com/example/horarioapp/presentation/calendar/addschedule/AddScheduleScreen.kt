package com.example.horarioapp.presentation.calendar.addschedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.horarioapp.core.ui.components.AppButton
import com.example.horarioapp.core.ui.components.AppTextField
import com.example.horarioapp.core.ui.components.TopBar
import com.example.horarioapp.core.ui.theme.TextSecondary

@Composable
fun AddScheduleScreen(
    userId: String,
    onNavigateBack: () -> Unit,
    onScheduleAdded: () -> Unit,
    viewModel: AddScheduleViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Bar
        TopBar(
            title = "Agregar Horario",
            onNavigateBack = onNavigateBack
        )

        // Contenido
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Error general
            if (state.error != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp)
                ) {
                    Column {
                        Text(
                            text = state.error ?: "",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            // Success message
            if (state.successMessage != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF34A853).copy(alpha = 0.1f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp)
                ) {
                    Text(
                        text = state.successMessage ?: "",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF34A853)
                    )
                }
            }

            // Materia
            AppTextField(
                value = state.subject,
                onValueChange = { viewModel.onEvent(AddScheduleEvent.OnSubjectChange(it)) },
                label = "Materia",
                placeholder = "ej: Cálculo Diferencial",
                error = state.subjectError,
                modifier = Modifier.fillMaxWidth()
            )

            // Profesor
            AppTextField(
                value = state.professor,
                onValueChange = { viewModel.onEvent(AddScheduleEvent.OnProfessorChange(it)) },
                label = "Profesor",
                placeholder = "ej: Dr. Ramírez",
                error = state.professorError,
                modifier = Modifier.fillMaxWidth()
            )

            // Aula
            AppTextField(
                value = state.classroom,
                onValueChange = { viewModel.onEvent(AddScheduleEvent.OnClassroomChange(it)) },
                label = "Aula",
                placeholder = "ej: Aula 301",
                error = state.classroomError,
                modifier = Modifier.fillMaxWidth()
            )

            // Día de la semana
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Día de la semana",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            viewModel.onEvent(AddScheduleEvent.OnToggleDayPicker)
                        }
                        .padding(12.dp)
                ) {
                    Text(
                        text = DAYS_OF_WEEK[state.selectedDay],
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Day picker dropdown
                if (state.showDayPicker) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        DAYS_OF_WEEK.forEachIndexed { index, day ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.onEvent(AddScheduleEvent.OnDaySelected(index))
                                    }
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = day,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }

            // Hora inicio
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Hora de inicio",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                AppTextField(
                    value = state.startTime,
                    onValueChange = { viewModel.onEvent(AddScheduleEvent.OnStartTimeChange(it)) },
                    placeholder = "HH:mm",
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Hora fin
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Hora de fin",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                AppTextField(
                    value = state.endTime,
                    onValueChange = { viewModel.onEvent(AddScheduleEvent.OnEndTimeChange(it)) },
                    placeholder = "HH:mm",
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Color selector
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Color",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AVAILABLE_COLORS.forEach { color ->
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    color = Color(android.graphics.Color.parseColor(color)),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable {
                                    viewModel.onEvent(AddScheduleEvent.OnColorSelected(color))
                                }
                                .then(
                                    if (state.selectedColor == color) {
                                        Modifier.padding(2.dp)
                                    } else {
                                        Modifier
                                    }
                                )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Botones inferiores
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AppButton(
                text = "Cancelar",
                onClick = onNavigateBack,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            )

            AppButton(
                text = "Guardar",
                onClick = { viewModel.onEvent(AddScheduleEvent.OnAddSchedule(userId)) },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                isLoading = state.isLoading
            )
        }
    }
}