package com.example.mobile_laboratoryproject2.viewModel.question_screen

data class QuestionUiState(
    val currentQuestion: Int = 0,
    val questionsAmount: Int = 0,
    var question: Question? = null,
    var timerValue: Float = 1f,
    val isFinished: Boolean = false
)
