package com.example.horarioapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.horarioapp.presentation.auth.login.LoginScreen
import com.example.horarioapp.presentation.home.HomeScreen
import com.example.horarioapp.presentation.calendar.CalendarScreen
import com.example.horarioapp.presentation.profile.ProfileScreen
import com.example.horarioapp.presentation.calendar.addschedule.AddScheduleScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route) {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Routes.Calendar.route) {
            CalendarScreen(
                navController = navController,
                onNavigateToAddSchedule = {
                    navController.navigate(Routes.AddSchedule.route)
                }
            )
        }
        composable(Routes.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(Routes.AddSchedule.route) {
            AddScheduleScreen(
                userId = "test_user_123", // Dummy ID por ahora
                onNavigateBack = { navController.popBackStack() },
                onScheduleAdded = { navController.popBackStack() }
            )
        }
    }
}