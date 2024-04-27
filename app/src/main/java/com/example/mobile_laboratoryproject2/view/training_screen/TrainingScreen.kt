package com.example.mobile_laboratoryproject2.view.training_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.mobile_laboratoryproject2.view.common.NavBar
import com.example.mobile_laboratoryproject2.viewModel.navigation.Destination
import com.example.mobile_laboratoryproject2.viewModel.training_screen.TrainingViewModel
import org.koin.androidx.compose.koinViewModel

// Экран повторения слов
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrainingScreen(
    onDictionaryClick: () -> Unit,
    onVideoClick: () -> Unit,
    onTrainingStart: () -> Unit,
    viewModel: TrainingViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(null) {
        viewModel.uiState.collect {
            if (it.isCountdownCompleted) {
                onTrainingStart()
            }
        }
    }

    Scaffold(
        bottomBar = {
            Column {
                if (!uiState.isStarted && uiState.areEnoughWords)
                    StartButton()

                NavBar(
                    currentScreen = Destination.TrainingScreen,
                    onDictionaryClick = onDictionaryClick,
                    onVideoClick = onVideoClick
                )
            }
        }
    ) {
        if (uiState.areEnoughWords)
            StartTrainingScreen()
        else
            TrainingPlaceholder()
    }
}