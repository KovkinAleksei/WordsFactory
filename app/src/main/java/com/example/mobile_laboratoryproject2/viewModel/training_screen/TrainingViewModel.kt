package com.example.mobile_laboratoryproject2.viewModel.training_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.domain.use_cases.training_screen.TrainingUseCase
import com.example.mobile_laboratoryproject2.ui.theme.GoColor
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
) : ViewModel() {
    private val _uiState = MutableStateFlow(TrainingUiState())
    val uiState = _uiState.asStateFlow()

    // Получение кол-ва слов в словаре
    init {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                val wordsAmount = trainingUseCase.getWordsAmount()

                currentState.copy(
                    wordsAmount = wordsAmount,
                    areEnoughWords = wordsAmount >= 3
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

        _uiState.update { currentState ->
            currentState.copy(isCountdownCompleted = true)
        }
    }

    // Отсчёт
    private suspend fun countDown() {
        val timerValues = listOf(
            R.string.five,
            R.string.four,
            R.string.three,
            R.string.two,
            R.string.one,
            R.string.go
        )

        for (timerValue in timerValues) {
            changeColor(timerValue)

            _uiState.update { currentState ->
                currentState.copy(
                    countDownProgress = if (timerValue != R.string.go)
                        1f
                    else
                        0f,
                    timerValue = timerValue
                )
            }

            for (i in 1..100) {
                if (timerValue != R.string.go) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            countDownProgress = _uiState.value.countDownProgress - 0.01f
                        )
                    }
                }

                delay(10)
            }
        }
    }

    // Изменение цвета таймера
    private fun changeColor(second: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                timerColor =
                when (second) {
                    R.string.five -> PrimaryColor
                    R.string.four -> TimerBlue
                    R.string.three -> TimerGreen
                    R.string.two -> TimerYellowColor
                    R.string.one -> TimerRedColor
                    else -> GoColor
                }
            )
        }

    }
}