package com.example.mobile_laboratoryproject2.domain.use_cases.question_screen

import java.time.LocalDate

interface IQuestionRepository {
    suspend fun getQuestoinList(): List<QuestionDto>
    suspend fun increaseLearningCoefficient(word: String)
    suspend fun decreaseLearningCoefficient(word: String)
    suspend fun updateLastTestCompletedDate(date: String)
}