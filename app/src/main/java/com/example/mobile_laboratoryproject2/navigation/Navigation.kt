package com.example.mobile_laboratoryproject2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobile_laboratoryproject2.view.dictionary_screen.DictionaryScreen
import com.example.mobile_laboratoryproject2.view.login_screen.LoginScreen
import com.example.mobile_laboratoryproject2.view.on_boarding_screen.OnBoardingScreen
import com.example.mobile_laboratoryproject2.view.sign_up_screen.SignUpScreen

@Composable
fun Navigation()
{
    val navController = rememberNavController()

    NavHost(
        navController = navController,
    //    startDestination = Destination.OnBoardingScreen.name
        startDestination = Destination.DictionaryScreen.name
    ) {
        composable(Destination.OnBoardingScreen.name) {
            OnBoardingScreen(navController)
        }

        composable(Destination.SignUpScreen.name) {
            SignUpScreen(navController)
        }

        composable(Destination.LoginScreen.name) {
            LoginScreen(navController)
        }

        composable(Destination.DictionaryScreen.name) {
            DictionaryScreen(navController)
        }
    }
}