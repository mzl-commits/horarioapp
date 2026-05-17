package com.example.horarioapp.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.horarioapp.core.navigation.AppBottomNavigation
import com.example.horarioapp.core.navigation.Routes
import com.example.horarioapp.core.ui.theme.BrandDarkBlue
import com.example.horarioapp.core.ui.theme.BrandOrange

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val user = state.user

    LaunchedEffect(state.isLoggedOut) {
        if (state.isLoggedOut) {
            navController.navigate(Routes.Login.route) {
                popUpTo(0)
            }
        }
    }

    Scaffold(
        bottomBar = { AppBottomNavigation(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFAFAFA))
        ) {
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
                        Text(
                            text = user?.getInitials().orEmpty().ifBlank { "U" },
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = BrandOrange
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = user?.fullName ?: "Estudiante",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = user?.email.orEmpty(),
                        fontSize = 16.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                ProfileOption(icon = Icons.Default.Settings, title = "Configuración", onClick = { })
                Spacer(modifier = Modifier.height(16.dp))
                ProfileOption(
                    icon = Icons.Default.ExitToApp,
                    title = "Cerrar sesión",
                    onClick = viewModel::logout,
                    isDestructive = true
                )
            }
        }
    }
}

@Composable
fun ProfileOption(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit,
    isDestructive: Boolean = false
) {
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
