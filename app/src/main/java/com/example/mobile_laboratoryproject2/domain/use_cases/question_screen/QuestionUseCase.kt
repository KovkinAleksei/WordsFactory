package com.example.mobile_laboratoryproject2.domain.use_cases.question_screen

import com.example.mobile_laboratoryproject2.R
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
    suspend fun answer(answer: String) {
        if (_answers.size == _questions.size)
            return

        _answers.add(answer)

        // Изменение коэффициента изучения солова
        val correctAnswer = _questions[_answers.size - 1].correctAnswer

        if (answer == _questions[_answers.size - 1].correctAnswer) {
            questionRepository.increaseLearningCoefficient(correctAnswer)
        }
        else {
            questionRepository.decreaseLearningCoefficient(correctAnswer)
        }
    }

    // Загрузка всего теста
    private suspend fun getAllQuestions() {
        _questions = questionRepository.getQuestoinList()

        val tempList = _questions.toMutableList()
        tempList.shuffle()
        _questions = tempList.toList()
    }

    // Получение вариантов ответа для вопроса в тесте
    suspend fun getTestQuestion(questionIndex: Int): Question {
        if (_questions.isEmpty())
            getAllQuestions()

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
    private fun getOptionName(index: Int): Int {
        return when(index) {
            0 -> R.string.optionA
            1 -> R.string.optionB
            else -> R.string.optionC
        }
    }
}