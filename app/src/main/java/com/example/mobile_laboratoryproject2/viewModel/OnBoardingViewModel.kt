package com.example.mobile_laboratoryproject2.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
): ViewModel() {
    val uiState = MutableStateFlow(OnBoardingUiState())

    fun onNextClick()
    {
        uiState.value = uiState.value.copy(currentPage = uiState.value.currentPage + 1)
    }
}