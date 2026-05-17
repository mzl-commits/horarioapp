package com.example.horarioapp.core.navigation

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Login : Routes("login")
    object Register : Routes("register")
    object Home : Routes("home")
    object Calendar : Routes("calendar")
    object Profile : Routes("profile")
    object AddSchedule : Routes("add_schedule")
}
