package com.example.mobile_laboratoryproject2.domain.use_cases.question_screen

// Вопрос теста
data class QuestionDto(
    val correctAnswer: String,
    val definition: String,
    val additionalWords: MutableList<String>
)
