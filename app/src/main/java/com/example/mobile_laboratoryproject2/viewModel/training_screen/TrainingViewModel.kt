package com.example.mobile_laboratoryproject2.viewModel.training_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_laboratoryproject2.domain.use_cases.training_screen.TrainingUseCase
import com.example.mobile_laboratoryproject2.ui.theme.PrimaryColor
import com.example.mobile_laboratoryproject2.ui.theme.TimerBlue
import com.example.mobile_laboratoryproject2.ui.theme.TimerGreen
import com.example.mobile_laboratoryproject2.ui.theme.TimerRedColor
import com.example.mobile_laboratoryproject2.ui.theme.TimerYellowColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TrainingViewModel(
    private val trainingUseCase: TrainingUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(TrainingUiState())
    val uiState = _uiState.asStateFlow()

    // Получение кол-ва слов в словаре
    init {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                currentState.copy(
                    wordsAmount = trainingUseCase.getWordsAmount()
                )
            }
        }
    }

    // Запуск повторения слов
    fun onStartButtonClick() {
        _uiState.update { currentState ->
            currentState.copy(isStarted = true)

        }

        val countDown = viewModelScope.launch(Dispatchers.Default) {
            countDown()
        }
    }

    // Отсчёт
    private suspend fun countDown() {
        for (second in 5 downTo 1) {
            changeColor(second)

            _uiState.update { currentState ->
                currentState.copy(
                    countDownProgress = 1f,
                    timer = second
                )
            }

            for (i in 1..100) {
                _uiState.update { currentState ->
                    currentState.copy(
                        countDownProgress = _uiState.value.countDownProgress - 0.01f
                    )
                }
                delay(10)
            }
        }
    }

    private fun changeColor(second: Int) {
        _uiState.update { currentState ->
            currentState.copy(timerColor =
                when(second){
                    5 -> PrimaryColor
                    4 -> TimerBlue
                    3 -> TimerGreen
                    2 -> TimerYellowColor
                    1 -> TimerRedColor
                    else -> TimerRedColor
                }
            )
        }

    }
}