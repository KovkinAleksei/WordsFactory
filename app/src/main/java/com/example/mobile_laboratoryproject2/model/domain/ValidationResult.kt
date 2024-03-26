package com.example.mobile_laboratoryproject2.model.domain

// Результат валидации
data class ValidationResult(
    val isCorrect: Boolean,
    val errorMessage: Int
)
