package com.example.mobile_laboratoryproject2.viewModel.on_boarding_screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OnBoardingViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(OnBoardingUiState())
    val uiState = _uiState.asStateFlow()

    // Пролистывание страниц нажатием кнопки Next
    fun onNextClick()
    {
        _uiState.update { currentState ->
            currentState.copy(currentPage = currentState.currentPage + 1)
        }
    }

    // Пролистывание страниц свайпом
    fun onPagerScroll(selectedPage: Int)
    {
        _uiState.update { currentState ->
            currentState.copy(currentPage = selectedPage)
        }
    }
}