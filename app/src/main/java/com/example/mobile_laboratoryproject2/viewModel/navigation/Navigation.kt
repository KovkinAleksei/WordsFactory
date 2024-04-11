package com.example.mobile_laboratoryproject2.viewModel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobile_laboratoryproject2.view.dictionary_screen.DictionaryScreen
import com.example.mobile_laboratoryproject2.view.finish_screen.FinishScreen
import com.example.mobile_laboratoryproject2.view.login_screen.LoginScreen
import com.example.mobile_laboratoryproject2.view.on_boarding_screen.OnBoardingScreen
import com.example.mobile_laboratoryproject2.view.question_screen.QuestionScreen
import com.example.mobile_laboratoryproject2.view.sign_up_screen.SignUpScreen
import com.example.mobile_laboratoryproject2.view.training_screen.TrainingScreen
import com.example.mobile_laboratoryproject2.view.video_screen.VideoScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        //startDestination = Destination.OnBoardingScreen.name
          startDestination = Destination.DictionaryScreen.name
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
                },
                onVideoClick = {
                    navController.navigate(Destination.VideoScreen.name)
                }
            )
        }

        // Экран повторения слов
        composable(Destination.TrainingScreen.name) {
            TrainingScreen(
                onDictionaryClick = {
                    navController.navigate(Destination.DictionaryScreen.name) {
                        popUpTo(Destination.DictionaryScreen.name) {
                            inclusive = true
                        }
                    }
                },
                onTrainingStart = {
                    navController.navigate(Destination.QuestionScreen.name) {
                        popUpTo(Destination.DictionaryScreen.name)
                    }
                },
                onVideoClick = {
                    navController.navigate(Destination.VideoScreen.name) {
                        popUpTo(Destination.DictionaryScreen.name)
                    }
                }
            )
        }

        // Экран прохождения теста
        composable(Destination.QuestionScreen.name) {
            QuestionScreen(
                onTestFinish = { correct, incorrect ->
                    navController.navigate("${Destination.FinishScreen.name}/${correct}/${incorrect}") {
                        popUpTo(Destination.DictionaryScreen.name)
                    }
                },
                onBackButtonClick = {
                    navController.navigate(Destination.DictionaryScreen.name) {
                        popUpTo(Destination.DictionaryScreen.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Экран результатов теста
        composable(
            "${Destination.FinishScreen.name}/{correct}/{incorrect}",
            arguments = listOf(
                navArgument("correct") {type = NavType.IntType },
                navArgument("incorrect") { type = NavType.IntType }
            )
        ) {navBackStackEntry ->
            val correct = navBackStackEntry.arguments?.getInt("correct") ?: 0
            val incorrect = navBackStackEntry.arguments?.getInt("incorrect") ?: 0

            FinishScreen(
                correct = correct,
                incorrect = incorrect,
                onAgainClick = {
                    navController.navigate(Destination.QuestionScreen.name) {
                        popUpTo(Destination.DictionaryScreen.name)
                    }
                },
                onBackButtonClick = {
                    navController.navigate(Destination.DictionaryScreen.name) {
                        popUpTo(Destination.DictionaryScreen.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Экран посещения сайта
        composable(Destination.VideoScreen.name) {
            VideoScreen(
                onTrainingClick = {
                    navController.navigate(Destination.TrainingScreen.name) {
                        popUpTo(Destination.DictionaryScreen.name)
                    }
                },
                onDictionaryClick = {
                    navController.navigate(Destination.DictionaryScreen.name) {
                        popUpTo(Destination.DictionaryScreen.name) {
                            inclusive = true
                        }
                    }
                },
                onClose = {
                    navController.popBackStack()
                }
            )
        }
    }
}