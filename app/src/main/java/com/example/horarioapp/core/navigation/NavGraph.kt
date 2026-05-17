package com.example.horarioapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.horarioapp.presentation.auth.login.LoginScreen
import com.example.horarioapp.presentation.auth.register.RegisterScreen
import com.example.horarioapp.presentation.home.HomeScreen
import com.example.horarioapp.presentation.calendar.CalendarScreen
import com.example.horarioapp.presentation.profile.ProfileScreen
import com.example.horarioapp.presentation.calendar.addschedule.AddScheduleScreen
import com.example.horarioapp.presentation.splash.SplashScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route
    ) {
        composable(Routes.Splash.route) {
            SplashScreen(
                onAuthenticated = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                },
                onUnauthenticated = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.Login.route) {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Routes.Register.route)
                }
            )
        }
        composable(Routes.Register.route) {
            RegisterScreen(
                onNavigateToHome = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
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
                onNavigateBack = { navController.popBackStack() },
                onScheduleAdded = { navController.popBackStack() }
            )
        }
    }
}
