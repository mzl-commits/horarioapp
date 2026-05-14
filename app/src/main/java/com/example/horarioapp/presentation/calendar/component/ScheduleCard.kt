package com.example.horarioapp.presentation.calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScheduleCard(time: String, title: String, subtitle: String, color: Color, indicatorColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        TimeColumn(time = time)
        
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