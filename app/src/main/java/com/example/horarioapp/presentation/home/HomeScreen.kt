package com.example.horarioapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.horarioapp.core.navigation.Routes
import com.example.horarioapp.core.ui.components.BrandDarkBlue
import com.example.horarioapp.core.ui.components.BrandOrange

import com.example.horarioapp.core.navigation.AppBottomNavigation

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { AppBottomNavigation(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            // Orange Header Background
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
                // Header Content
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 48.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = Color.White,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "JR",
                                    color = BrandOrange,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(text = "Buenos días,", color = Color.White, fontSize = 14.sp)
                            Text(text = "Juan Rojas", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
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

                // Dark Summary Card
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
                        // Fake progress circle
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(80.dp)
                        ) {
                            CircularProgressIndicator(
                                progress = { 0.75f },
                                modifier = Modifier.size(80.dp),
                                color = Color(0xFF66BB6A),
                                strokeWidth = 8.dp,
                                trackColor = Color.DarkGray
                            )
                            Text(text = "75%", color = Color.White, fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.width(24.dp))

                        Column {
                            Text(text = "Hoy", color = Color.White, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "5 clases totales", color = Color.LightGray, fontSize = 14.sp)
                            Text(text = "4 completadas", color = Color.LightGray, fontSize = 14.sp)
                            Text(text = "1 pendiente", color = Color.LightGray, fontSize = 14.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Next Classes Section
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Próximas clases", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    TextButton(onClick = { }) {
                        Text(text = "Ver todo", color = BrandOrange)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Class Card 1
                ClassCard(
                    title = "Cálculo Diferencial",
                    time = "10:00 - 12:00 • Aula 301",
                    status = "Ahora",
                    statusColor = BrandOrange
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Class Card 2
                ClassCard(
                    title = "Física General",
                    time = "13:00 - 15:00 • Lab B2",
                    status = "Siguiente",
                    statusColor = Color(0xFF42A5F5)
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun ClassCard(title: String, time: String, status: String, statusColor: Color) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(statusColor, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = status, color = statusColor, fontSize = 12.sp, fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = time, color = Color.Gray, fontSize = 14.sp)
        }
    }
}