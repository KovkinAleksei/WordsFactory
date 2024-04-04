package com.example.mobile_laboratoryproject2.domain.use_cases.question_screen

import com.example.mobile_laboratoryproject2.viewModel.question_screen.AnswerOption
import com.example.mobile_laboratoryproject2.viewModel.question_screen.Question

class QuestionUseCase(
    private val questionRepository: IQuestionRepository
) {
    private var _questions: List<QuestionDto> = listOf()
    private val _answers: MutableList<String> = mutableListOf()

    // Получение кол-ва верных ответов
    fun getCorrectCount(): Int {
        return _answers.filterIndexed { index, answer ->
            answer == _questions[index].correctAnswer
        }.size
    }

    // Получение кол-ва неверных ответов
    fun getIncorrectCount(): Int {
        return _answers.filterIndexed { index, answer ->
            answer != _questions[index].correctAnswer
        }.size
    }

    // Сохранение ответа на вопрос
    fun answer(answer: String?) {
        _answers.add(answer ?: "")
    }

    // Загрузка всего теста
    private suspend fun getQuestions(){
        _questions = questionRepository.getQuestoinList()
    }

    // Получение вариантов ответа для вопроса в тесте
    suspend fun getAnswerOptions(questionIndex: Int): Question {
        if (_questions.isEmpty())
            getQuestions()

        val question = _questions[questionIndex]

        val words = question.additionalWords
        words.add(question.correctAnswer)
        words.shuffle()

        return Question(
            definition = question.definition,
            answerOptions = words.mapIndexed { index, it ->
                AnswerOption(
                    word = it,
                    optionName = getOptionName(index)
                )
            }
        )
    }

    // Получение кол-ва вопросов в тесте
    fun getQuestionsAmount(): Int {
        return _questions.size
    }

    // Получение нумерации варианта ответа
    private fun getOptionName(index: Int): String {
        return when(index) {
            0 -> "A."
            1 -> "B."
            else -> "C."
        }
    }
}