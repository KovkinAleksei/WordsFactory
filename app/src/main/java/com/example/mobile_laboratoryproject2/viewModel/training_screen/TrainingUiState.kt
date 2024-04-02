package com.example.mobile_laboratoryproject2.viewModel.training_screen

import androidx.compose.ui.graphics.Color
import com.example.mobile_laboratoryproject2.ui.theme.PrimaryColor

data class TrainingUiState(
    val wordsAmount: Int = 0,
    val timer: Int = 5,
    val isStarted: Boolean = false,
    val countDownProgress: Float = 1f,
    val timerColor: Color = PrimaryColor
)
