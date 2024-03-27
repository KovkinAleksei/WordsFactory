package com.example.mobile_laboratoryproject2.model.domain.entities

// Результат валидации
data class ValidationResult(
    val isCorrect: Boolean,
    val errorMessage: Int
)
