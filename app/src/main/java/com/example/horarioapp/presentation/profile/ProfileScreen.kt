package com.example.horarioapp.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Settings
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
import com.example.horarioapp.core.navigation.AppBottomNavigation
import com.example.horarioapp.core.ui.theme.BrandDarkBlue
import com.example.horarioapp.core.ui.theme.BrandOrange

@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { AppBottomNavigation(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFAFAFA))
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BrandOrange)
                    .padding(vertical = 48.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "JR", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = BrandOrange)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Juan Rojas", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text(text = "juan.rojas@uni.edu.pe", fontSize = 16.sp, color = Color.White.copy(alpha = 0.8f))
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Options
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                ProfileOption(icon = Icons.Default.Settings, title = "Configuración", onClick = { })
                Spacer(modifier = Modifier.height(16.dp))
                ProfileOption(icon = Icons.Default.ExitToApp, title = "Cerrar sesión", onClick = { }, isDestructive = true)
            }
        }
    }
}

@Composable
fun ProfileOption(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, onClick: () -> Unit, isDestructive: Boolean = false) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 2.dp,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isDestructive) Color.Red else BrandDarkBlue
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = if (isDestructive) Color.Red else Color.Black
            )
        }
    }
}
