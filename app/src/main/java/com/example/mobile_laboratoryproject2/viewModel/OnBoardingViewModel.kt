package com.example.mobile_laboratoryproject2.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
): ViewModel() {
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

    // Переход с приветственного экрана
    fun onLetsStartClick()
    {
        // TODO
    }

    // Пропуск приветственного экрана
    fun onSkipClick()
    {
        // TODO
    }
}