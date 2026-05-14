package com.example.horarioapp.presentation.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
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

@Composable
fun CalendarScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Inicio") },
                    selected = false,
                    onClick = { 
                        navController.navigate(Routes.Home.route) {
                            popUpTo(Routes.Home.route) { inclusive = true }
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                    label = { Text("Horario") },
                    selected = true,
                    onClick = { },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BrandOrange,
                        selectedTextColor = BrandOrange,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("Perfil") },
                    selected = false,
                    onClick = { }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFAFAFA))
        ) {
            // Top App Bar Area
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Mié, 14 Mayo", color = Color.Gray, fontSize = 14.sp)
                    Text(text = "Horario semanal", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .background(BrandOrange, shape = CircleShape)
                        .size(48.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Añadir", tint = Color.White)
                }
            }

            // Days of the week row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DayItem("Lun", "12", false)
                DayItem("Mar", "13", false)
                DayItem("Mié", "14", true)
                DayItem("Jue", "15", false)
                DayItem("Vie", "16", false)
                DayItem("Sáb", "17", false)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Schedule Timeline
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                item {
                    ScheduleCard(
                        time = "8:00",
                        title = "Cálculo Diferencial",
                        subtitle = "Aula 301 - Dr. Ramírez",
                        color = Color(0xFFE3F2FD),
                        indicatorColor = Color(0xFF42A5F5)
                    )
                }
                item {
                    ScheduleCard(
                        time = "10:00",
                        title = "Programación I",
                        subtitle = "Lab C1 - Ing. Torres",
                        color = Color(0xFFFFF3E0),
                        indicatorColor = BrandOrange
                    )
                }
                item {
                    ScheduleCard(
                        time = "13:00",
                        title = "Física General",
                        subtitle = "Lab B2 - Dr. Vargas",
                        color = Color(0xFFE8F5E9),
                        indicatorColor = Color(0xFF66BB6A)
                    )
                }
                item {
                    ScheduleCard(
                        time = "15:00",
                        title = "Álgebra Lineal",
                        subtitle = "Aula 204 - Dra. Lima",
                        color = Color(0xFFF3E5F5),
                        indicatorColor = Color(0xFFAB47BC)
                    )
                }
                item { Spacer(modifier = Modifier.height(32.dp)) }
            }
        }
    }
}

@Composable
fun DayItem(dayName: String, dayNumber: String, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) BrandDarkBlue else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clickable { }
    ) {
        Text(
            text = dayName,
            color = if (isSelected) Color.White else Color.Gray,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = dayNumber,
            color = if (isSelected) Color.White else Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ScheduleCard(time: String, title: String, subtitle: String, color: Color, indicatorColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = time,
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier
                .width(48.dp)
                .padding(top = 8.dp)
        )
        
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(16.dp),
            color = color
        ) {
            Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
                // Colored left border indicator
                Box(
                    modifier = Modifier
                        .width(6.dp)
                        .fillMaxHeight()
                        .background(indicatorColor)
                )
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = subtitle, color = Color.DarkGray, fontSize = 14.sp)
                }
            }
        }
    }
}