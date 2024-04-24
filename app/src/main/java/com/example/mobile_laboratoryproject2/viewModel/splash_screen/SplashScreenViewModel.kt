package com.example.mobile_laboratoryproject2.viewModel.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_laboratoryproject2.domain.use_cases.splash_screen.SplashUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val splashUseCase: SplashUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SplashScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        // Проверка первого входа в приложение
        viewModelScope.launch {
            val onBoardingShowFlow = splashUseCase.isOnBoardingShown()
            var isCollected = false

            onBoardingShowFlow.collectLatest {
                if (!isCollected) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isOnBoardingShown = it.isOnBoardingShown,
                            isCompleted = true
                        )
                    }

                    isCollected = true
                }

                splashUseCase.updateOnBoarding()
            }
        }
    }
}
