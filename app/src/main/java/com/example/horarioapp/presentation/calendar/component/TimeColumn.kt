package com.example.horarioapp.presentation.calendar.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimeColumn(time: String) {
    Text(
        text = time,
        color = Color.Gray,
        fontSize = 14.sp,
        modifier = Modifier
            .width(48.dp)
            .padding(top = 8.dp)
    )
}
