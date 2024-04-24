package com.example.mobile_laboratoryproject2.domain.use_cases.widget

interface IWidgetRepository {
    suspend fun getWordsAmount(): Int
    suspend fun getLearnedWords(): Int
}