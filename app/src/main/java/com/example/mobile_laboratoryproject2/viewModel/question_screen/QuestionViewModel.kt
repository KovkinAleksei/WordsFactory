package com.example.mobile_laboratoryproject2.viewModel.question_screen

import androidx.lifecycle.ViewModel
import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.WordModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuestionViewModel(

): ViewModel() {
    private val _uiState = MutableStateFlow(QuestionUiState())
    val uiState = _uiState.asStateFlow()

    fun onAnswerOptionChoose(answer: AnswerOption) {
        val newOptions = _uiState.value.answerOptions.toMutableList().map {
            AnswerOption(
                isChosen = it.word == answer.word,
                word = it.word,
                optionName = it.optionName
            )
        }

        _uiState.update { currentState ->
            currentState.copy(answerOptions = newOptions)
        }
    }
}