package com.example.mobile_laboratoryproject2.data.repositories

import com.example.mobile_laboratoryproject2.data.local_data_source.QuestionDao
import com.example.mobile_laboratoryproject2.data.local_data_source.TestPreferencesStore
import com.example.mobile_laboratoryproject2.domain.entities.database_entities.DefinitionEntity
import com.example.mobile_laboratoryproject2.domain.entities.database_entities.WordEntity
import com.example.mobile_laboratoryproject2.domain.use_cases.question_screen.IQuestionRepository
import com.example.mobile_laboratoryproject2.domain.use_cases.question_screen.QuestionDto
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.random.Random

class QuestionRepositoryImpl(
    private val questionDao: QuestionDao
) : KoinComponent, IQuestionRepository {
    private val testPrefs by inject<TestPreferencesStore>()

    // Список вопросов для теста
    override suspend fun getQuestoinList(): List<QuestionDto> {
        val testQuestions = mutableListOf<QuestionDto>()

        // Получение слов для повторения
        val testAnswers = questionDao.getTestAnswers()

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
        return questionDao.getDefinitions(word.id!!)
    }

    // Неверные варианты ответа
    private suspend fun getAdditionalWords(word: String): List<WordEntity> {
        return questionDao.getAdditionalWords(word)
    }

    // Увеличение коэффициента изучения слова
    override suspend fun increaseLearningCoefficient(word: String) {
        val wordEntity = questionDao.getWord(word)

        if (wordEntity != null)
            questionDao.updateWord(wordEntity.copy(learningCoefficient = wordEntity.learningCoefficient + 1))
    }

    // Уменьшение коэффициента изучения слова
    override suspend fun decreaseLearningCoefficient(word: String) {
        val wordEntity = questionDao.getWord(word)

        if (wordEntity != null)
            questionDao.updateWord(wordEntity.copy(learningCoefficient = wordEntity.learningCoefficient - 1))
    }

    // Обновление дня прохождения теста
    override suspend fun updateLastTestCompletedDate(date: String) {
        testPrefs.store.updateData {
            it.toBuilder().setLastCompletedDate(date).build()
        }
    }
}