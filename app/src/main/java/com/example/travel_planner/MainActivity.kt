package com.example.travel_planner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travel_planner.data.SupabaseProvider
import com.example.travel_planner.navigation.AppDestination
import com.example.travel_planner.ui.screens.DashboardScreen
import com.example.travel_planner.ui.screens.LoginScreen
import com.example.travel_planner.ui.screens.RegisterScreen
import com.example.travel_planner.ui.screens.WelcomeScreen
import com.example.travel_planner.ui.theme.Travel_PlannerTheme
import io.github.jan.supabase.auth.auth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enables edge-to-edge layout so system bars integrate seamlessly
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                // Helper lambda to navigate to Dashboard and clear auth screens from backstack
                val navigateToDashboard = {
                    navController.navigate(AppDestination.Dashboard.route) {
                        popUpTo(AppDestination.Welcome.route) { inclusive = true }
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = AppDestination.Welcome.route
                ) {
                    // Welcome Screen
                    composable(AppDestination.Welcome.route) {
                        WelcomeScreen(
                            onNavigateToRegister = {
                                navController.navigate(AppDestination.Register.route)
                            },
                            onNavigateToLogin = {
                                navController.navigate(AppDestination.Login.route)
                            }
                        )
                    }

                    // Login Screen
                    composable(AppDestination.Login.route) {
                        LoginScreen(
                            onNavigateBackToWelcome = {
                                navController.popBackStack(AppDestination.Welcome.route, inclusive = false)
                            },
                            onNavigateToRegister = {
                                navController.navigate(AppDestination.Register.route)
                            },
                            onNavigateToDashboard = navigateToDashboard
                        )
                    }

                    // Register Screen
                    composable(AppDestination.Register.route) {
                        RegisterScreen(
                            onNavigateBackToWelcome = {
                                navController.popBackStack(AppDestination.Welcome.route, inclusive = false)
                            },
                            onNavigateToLogin = {
                                navController.navigate(AppDestination.Login.route)
                            },
                            onNavigateToDashboard = navigateToDashboard
                        )
                    }

                    // Dashboard Screen (Guarded)
                    composable(AppDestination.Dashboard.route) {
                        val isUserLoggedIn = SupabaseProvider.client.auth.currentUserOrNull() != null

                        // If not authenticated, prevent viewing dashboard and redirect to Welcome
                        if (!isUserLoggedIn) {
                            LaunchedEffect(Unit) {
                                navController.navigate(AppDestination.Welcome.route) {
                                    popUpTo(0) { inclusive = true }
                                }
                            }
                        } else {
                            DashboardScreen(
                                onSignOut = {
                                    navController.navigate(AppDestination.Welcome.route) {
                                        popUpTo(0) { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Travel_PlannerTheme {
        Greeting("Android")
    }
}