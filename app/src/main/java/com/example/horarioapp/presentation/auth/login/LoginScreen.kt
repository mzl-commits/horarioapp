package com.example.horarioapp.presentation.auth.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.horarioapp.core.ui.components.BrandOrange

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandOrange)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Iniciar sesión",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(
                    color = BrandOrange,
                    modifier = Modifier.width(40.dp).height(2.dp)
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(text = "Correo", fontWeight = FontWeight.Medium, color = Color.DarkGray)
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("correo@uni.edu.pe") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BrandOrange,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(text = "Contraseña", fontWeight = FontWeight.Medium, color = Color.DarkGray)
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("••••••••") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BrandOrange,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = { rememberMe = it },
                            colors = CheckboxDefaults.colors(checkedColor = BrandOrange)
                        )
                        Text(text = "Recuérdame", color = Color.Gray, fontSize = 14.sp)
                    }
                    Text(
                        text = "¿Olvidaste tu\ncontraseña?",
                        color = BrandOrange,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { }
                    )
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Button(
                    onClick = { onNavigateToHome() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color.LightGray),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Ingresar", color = Color.LightGray, fontSize = 16.sp)
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "¿No tienes cuenta? ", color = Color.Gray)
                    Text(
                        text = "Regístrate",
                        color = BrandOrange,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { }
                    )
                }
            }
        }
    }
}