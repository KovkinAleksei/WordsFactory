package com.example.mobile_laboratoryproject2.domain.use_cases.training_screen

interface ITrainingRepository {
    suspend fun getWordsAmount(): Int
}