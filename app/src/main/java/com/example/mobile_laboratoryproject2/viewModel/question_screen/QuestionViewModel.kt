package com.example.mobile_laboratoryproject2.viewModel.question_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_laboratoryproject2.domain.use_cases.question_screen.QuestionUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuestionViewModel(
    private val questionUseCase: QuestionUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(QuestionUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getNextQuestion()
            countDown()
        }
    }

    // Получение следующего вопроса
    private suspend fun getNextQuestion() {
        _uiState.update {currentState ->
            currentState.copy(
                question = questionUseCase.getAnswerOptions(_uiState.value.currentQuestion),
                currentQuestion = _uiState.value.currentQuestion + 1,
                questionsAmount = questionUseCase.getQuestionsAmount(),
                timerValue = 1f
            )
        }
    }

    // Выбор варианта ответа
    fun onAnswerOptionChoose(answer: AnswerOption) {
        // Выделение варианта ответа
        val newOptions = _uiState.value.question?.answerOptions?.toMutableList()?.map {
            it.copy(isChosen = it.word == answer.word)
        }

        _uiState.update { currentState ->
            currentState.copy(question = _uiState.value.question?.copy(answerOptions = newOptions!!))
        }

        // Сохранение ответа
        questionUseCase.answer(answer.word)

        // Переход к следующему вопросу
        viewModelScope.launch {
           // delay(100)
            getNextQuestion()
        }
    }

    // Отсчёт времени на вопрос
    private suspend fun countDown() {
        while (_uiState.value.currentQuestion <= _uiState.value.questionsAmount) {
            _uiState.update { currentState ->
                currentState.copy(
                    timerValue = _uiState.value.timerValue - 0.002f
                )
            }
            delay(10)

            // Переход к следующему вопросу по истечении времени
            if (_uiState.value.timerValue <= 0f) {
                questionUseCase.answer("")
                getNextQuestion()
            }
        }
    }
}