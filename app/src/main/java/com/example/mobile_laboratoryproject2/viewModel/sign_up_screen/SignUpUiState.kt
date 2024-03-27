package com.example.mobile_laboratoryproject2.viewModel.sign_up_screen

import com.example.mobile_laboratoryproject2.R

data class SignUpUiState(
    val isHiddenPassword: Boolean = true,
    val areFieldValuesCorrect: Boolean = true,
    val errorMessage: Int = R.string.ok,
    val isSignedUp: Boolean = false
)
