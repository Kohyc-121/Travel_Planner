package com.example.travel_planner.navigation

sealed class AppDestination(val route: String) {
    data object Welcome : AppDestination("welcome_route")
    data object Login : AppDestination("login_route")
    data object Register : AppDestination("register_route")
    data object Dashboard : AppDestination("dashboard_route")
}