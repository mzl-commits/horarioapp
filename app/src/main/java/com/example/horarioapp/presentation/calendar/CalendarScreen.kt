package com.example.horarioapp.presentation.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.horarioapp.core.navigation.AppBottomNavigation
import com.example.horarioapp.core.ui.theme.BrandOrange
import com.example.horarioapp.presentation.calendar.component.CalendarGrid
import com.example.horarioapp.presentation.calendar.component.ScheduleCard

@Composable
fun CalendarScreen(
    navController: NavHostController,
    onNavigateToAddSchedule: () -> Unit = {},
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        bottomBar = { AppBottomNavigation(navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAddSchedule,
                containerColor = BrandOrange,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Añadir")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFAFAFA))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            ) {
                Text(text = "Horario semanal", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = "${state.schedules.size} clases guardadas",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            CalendarGrid(
                selectedDay = state.selectedDay,
                onDaySelected = viewModel::selectDay
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                if (state.schedulesForSelectedDay.isEmpty()) {
                    item {
                        Text(
                            text = "No hay clases para este día",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 32.dp)
                        )
                    }
                } else {
                    state.schedulesForSelectedDay.forEach { schedule ->
                        item(key = schedule.id) {
                            val indicator = parseColor(schedule.color)
                            ScheduleCard(
                                time = schedule.startTime,
                                title = schedule.subject,
                                subtitle = "${schedule.startTime} - ${schedule.endTime} · ${schedule.classroom} · ${schedule.professor}",
                                color = indicator.copy(alpha = 0.14f),
                                indicatorColor = indicator
                            )
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(96.dp)) }
            }
        }
    }
}

private fun parseColor(value: String): Color {
    return runCatching { Color(android.graphics.Color.parseColor(value)) }
        .getOrDefault(BrandOrange)
}
