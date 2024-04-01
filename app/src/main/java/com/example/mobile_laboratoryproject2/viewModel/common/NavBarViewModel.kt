package com.example.mobile_laboratoryproject2.viewModel.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NavBarViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(NavBarUiState())
    val uiState = _uiState.asStateFlow()

    // Переход на экрак Dictionary
    fun onDictionaryClick() {
        _uiState.update {currentState ->
            currentState.copy(selectedItem = NavBarItems.Dictionary)
        }
    }

    // Переход на экран Training
    fun onTrainingClick() {
        _uiState.update { currentState ->
            currentState.copy(selectedItem = NavBarItems.Training)
        }
    }

    // Переход на экран Video
    fun onVideoClick() {
        _uiState.update { currentState ->
            currentState.copy(selectedItem = NavBarItems.Video)
        }
    }
}