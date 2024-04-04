package com.example.mobile_laboratoryproject2.data.repositories

import com.example.mobile_laboratoryproject2.data.local_data_source.WordDao
import com.example.mobile_laboratoryproject2.domain.entities.database_entities.DefinitionEntity
import com.example.mobile_laboratoryproject2.domain.entities.database_entities.WordEntity
import com.example.mobile_laboratoryproject2.domain.use_cases.question_screen.IQuestionRepository
import com.example.mobile_laboratoryproject2.domain.use_cases.question_screen.QuestionDto
import kotlin.random.Random

class QuestionRepositoryImpl(
    private val wordDao: WordDao
): IQuestionRepository {

    // Список вопросов для теста
    override suspend fun getQuestoinList(): List<QuestionDto> {
        val testQuestions = mutableListOf<QuestionDto>()

        // Получение слов для повторения
        val testAnswers = wordDao.getTestAnswers()

        // Получение определения слова для повторения
        testAnswers.forEach {
            val definitionList = getDefinitions(it)

            val randomIndex = Random(System.currentTimeMillis()).nextInt(definitionList.size)
            val randomDefinition = definitionList[randomIndex]

            // Получение неверных вариантов ответа
            val additionalWordEntities = getAdditionalWords(it.word)

            // Получение вопроса
            testQuestions.add(
                QuestionDto(
                    correctAnswer = it.word,
                    definition = randomDefinition.definition,
                    additionalWords = (additionalWordEntities.map { it.word }).toMutableList()
                )
            )
        }

        return testQuestions
    }

    // Определение слова
    private suspend fun getDefinitions(word: WordEntity): List<DefinitionEntity> {
        return wordDao.getDefinitions(word.id!!)
    }

    // Неверные варианты ответа
    private suspend fun getAdditionalWords(word: String): List<WordEntity> {
        return wordDao.getAdditionalWords(word)
    }
}