package com.example.mobile_laboratoryproject2.viewModel.on_boarding_screen

data class OnBoardingUiState(
    val pagesCount: Int = 3,
    val currentPage: Int = 1,
    val isStarted: Boolean = false
)
