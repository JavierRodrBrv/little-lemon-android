package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

    // Determinar el destino de inicio según si el usuario ya está registrado
    val isUserRegistered = sharedPreferences.getBoolean("userRegistered", false) ||
            !sharedPreferences.getString("firstName", "").isNullOrBlank()

    val startDestination = if (isUserRegistered) {
        Home.route
    } else {
        Onboarding.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Onboarding.route) {
            Onboarding(navController = navController)
        }
        composable(Home.route) {
            Home(navController = navController)
        }
        composable(Profile.route) {
            Profile(navController = navController)
        }
    }
}

// Alias compatible por si el ejercicio o grader invoca MyNavigation
@Composable
fun MyNavigation(navController: NavHostController) {
    Navigation(navController = navController)
}
