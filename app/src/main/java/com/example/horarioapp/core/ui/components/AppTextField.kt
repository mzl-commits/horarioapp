package com.example.horarioapp.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.horarioapp.core.ui.theme.BrandOrange

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String = "",
    error: String? = null,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(modifier = modifier) {
        if (label != null) {
            Text(text = label, color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(bottom = 4.dp))
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.LightGray) },
            isError = error != null,
            visualTransformation = visualTransformation,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BrandOrange,
                unfocusedBorderColor = Color(0xFFE0E0E0)
            )
        )
        if (error != null) {
            Text(text = error, color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
        }
    }
}
