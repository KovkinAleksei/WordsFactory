package com.example.mobile_laboratoryproject2.viewModel.question_screen

// Вопрос теста
data class Question(
    val definition: String,
    val answerOptions: List<AnswerOption>
)
