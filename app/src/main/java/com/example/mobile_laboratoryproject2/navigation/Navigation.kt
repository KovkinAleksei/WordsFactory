package com.example.mobile_laboratoryproject2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobile_laboratoryproject2.view.OnBoardingScreen.OnBoardingScreen
import com.example.mobile_laboratoryproject2.view.SignUpScreen.SignUpScreen

@Composable
fun Navigation()
{
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.OnBoardingScreen.name
    ) {
        composable(Destination.OnBoardingScreen.name) {
            OnBoardingScreen(navController)
        }

        composable(Destination.SignUpScreen.name) {
            SignUpScreen()
        }
    }
}