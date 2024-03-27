package com.example.mobile_laboratoryproject2.viewModel.login_screen

import com.example.mobile_laboratoryproject2.R

data class LoginUiState(
    val isHiddenPassword: Boolean = true,
    val areFieldValuesCorrect: Boolean = true,
    val errorMessage: Int = R.string.ok
)
