package com.example.mobile_laboratoryproject2.viewModel.SignUpScreen

import com.example.mobile_laboratoryproject2.R

data class SignUpUiState(
    val isHiddenPassword: Boolean = false,
    val areFieldValuesCorrect: Boolean = true,
    val errorMessage: Int = R.string.ok
)
