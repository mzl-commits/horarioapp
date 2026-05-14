package com.example.horarioapp.presentation.calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.horarioapp.core.ui.components.BrandDarkBlue

@Composable
fun CalendarGrid() {
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
}

@Composable
private fun DayItem(dayName: String, dayNumber: String, isSelected: Boolean) {
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