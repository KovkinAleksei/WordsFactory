package com.example.mobile_laboratoryproject2.viewModel.question_screen

// Вариант ответа
data class AnswerOption(
    var isChosen: Boolean = false,
    val word: String,
    val optionName: Int
)
