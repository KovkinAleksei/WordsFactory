package com.example.mobile_laboratoryproject2.viewModel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobile_laboratoryproject2.view.dictionary_screen.DictionaryScreen
import com.example.mobile_laboratoryproject2.view.login_screen.LoginScreen
import com.example.mobile_laboratoryproject2.view.on_boarding_screen.OnBoardingScreen
import com.example.mobile_laboratoryproject2.view.question_screen.QuestionScreen
import com.example.mobile_laboratoryproject2.view.sign_up_screen.SignUpScreen
import com.example.mobile_laboratoryproject2.view.training_screen.TrainingScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
      //  startDestination = Destination.OnBoardingScreen.name
        //  startDestination = Destination.DictionaryScreen.name
        startDestination = Destination.QuestionScreen.name
    ) {
        // Начальный экран
        composable(Destination.OnBoardingScreen.name) {
            OnBoardingScreen(
                onSkipButtonClick = {
                    navController.navigate(Destination.SignUpScreen.name) {
                        popUpTo(Destination.OnBoardingScreen.name) {
                            inclusive = true
                        }
                    }
                },
                onStartButtonClick = {
                    navController.navigate(Destination.SignUpScreen.name) {
                        popUpTo(Destination.OnBoardingScreen.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Экран регистрации
        composable(Destination.SignUpScreen.name) {
            SignUpScreen(
                onSignUp = {
                    navController.navigate(Destination.DictionaryScreen.name) {
                        popUpTo(Destination.SignUpScreen.name) {
                            inclusive = true
                        }
                    }
                },
                onSignInButtonClick = {
                    navController.navigate(Destination.LoginScreen.name) {
                        popUpTo(Destination.SignUpScreen.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Экран авторизации
        composable(Destination.LoginScreen.name) {
            LoginScreen(
                onLogin = {
                    navController.navigate(Destination.DictionaryScreen.name) {
                        popUpTo(Destination.LoginScreen.name) {
                            inclusive = true
                        }
                    }
                },
                onSignUpButtonClick = {
                    navController.navigate(Destination.SignUpScreen.name) {
                        popUpTo(Destination.LoginScreen.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Экран словаря
        composable(Destination.DictionaryScreen.name) {
            DictionaryScreen(
                onTrainingClick = {
                    navController.navigate(Destination.TrainingScreen.name) {
                        popUpTo(Destination.DictionaryScreen.name)
                    }
                }
            )
        }

        // Экран повторения слов
        composable(Destination.TrainingScreen.name) {
            TrainingScreen(
                onDictionaryClick = {
                    navController.navigate(Destination.DictionaryScreen.name) {
                        popUpTo(Destination.TrainingScreen.name) {
                            inclusive = true
                        }
                    }
                },

                onTrainingStart = {
                    navController.navigate(Destination.QuestionScreen.name)
                }
            )
        }

        // Экран прохождения теста
        composable(Destination.QuestionScreen.name) {
            QuestionScreen()
        }
    }
}