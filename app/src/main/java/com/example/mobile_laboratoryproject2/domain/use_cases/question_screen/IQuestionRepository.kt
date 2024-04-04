package com.example.mobile_laboratoryproject2.domain.use_cases.question_screen

interface IQuestionRepository {
    suspend fun getQuestoinList(): List<QuestionDto>
}