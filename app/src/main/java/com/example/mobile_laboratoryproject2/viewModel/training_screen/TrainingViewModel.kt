package com.example.mobile_laboratoryproject2.viewModel.training_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_laboratoryproject2.domain.use_cases.training_screen.TrainingUseCase
import kotlinx.coroutines.Dispatchers
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
}