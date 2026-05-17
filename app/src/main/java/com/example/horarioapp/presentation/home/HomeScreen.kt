package com.example.horarioapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.horarioapp.core.navigation.AppBottomNavigation
import com.example.horarioapp.core.navigation.Routes
import com.example.horarioapp.core.ui.theme.BrandDarkBlue
import com.example.horarioapp.core.ui.theme.BrandOrange
import com.example.horarioapp.domain.model.Schedule

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val user = state.user
    val todaySchedules = state.todaySchedules
    val nextClasses = todaySchedules.take(2)
    val progress = if (todaySchedules.isEmpty()) 0f else 0.25f

    Scaffold(
        bottomBar = { AppBottomNavigation(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f)
                    .background(BrandOrange)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        color = Color.White,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = user?.getInitials().orEmpty().ifBlank { "U" },
                                color = BrandOrange,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Buenos días,", color = Color.White, fontSize = 14.sp)
                        Text(
                            text = user?.fullName ?: "Estudiante",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .background(Color.White.copy(alpha = 0.2f), shape = CircleShape)
                            .size(40.dp)
                    ) {
                        Icon(Icons.Default.Notifications, contentDescription = "Notificaciones", tint = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Organiza tu\nsemana académica",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    shape = RoundedCornerShape(24.dp),
                    color = BrandDarkBlue
                ) {
                    Row(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(80.dp)
                        ) {
                            CircularProgressIndicator(
                                progress = { progress },
                                modifier = Modifier.size(80.dp),
                                color = Color(0xFF66BB6A),
                                strokeWidth = 8.dp,
                                trackColor = Color.DarkGray
                            )
                            Text(
                                text = "${(progress * 100).toInt()}%",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.width(24.dp))

                        Column {
                            Text(text = "Hoy", color = Color.White, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "${todaySchedules.size} clases programadas",
                                color = Color.LightGray,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "${state.schedules.size} clases esta semana",
                                color = Color.LightGray,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Próximas clases",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.weight(1f)
                    )
                    TextButton(onClick = { navController.navigate(Routes.Calendar.route) }) {
                        Text(text = "Ver todo", color = BrandOrange)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (nextClasses.isEmpty()) {
                    Text(
                        text = "No tienes clases para hoy",
                        color = Color.Gray,
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                    )
                } else {
                    nextClasses.forEach { schedule ->
                        ClassCard(schedule = schedule)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun ClassCard(schedule: Schedule) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${schedule.startTime} - ${schedule.endTime}",
                color = BrandOrange,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = schedule.subject, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${schedule.classroom} · ${schedule.professor}",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}
