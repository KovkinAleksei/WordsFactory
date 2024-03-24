package com.example.mobile_laboratoryproject2.viewModel.SignUpScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()

    val name = mutableStateOf(TextFieldValue(""))
    val email = mutableStateOf(TextFieldValue(""))
    val password = mutableStateOf(TextFieldValue(""))

    // Ввод имени пользователя
    fun handleNameInput(input: TextFieldValue) {
        name.value = input
    }

    // Ввод email пользователя
    fun handleEmailInput(input: TextFieldValue) {
        email.value = input
    }

    // Ввод пароля пользователя
    fun handlePasswordInput(input: TextFieldValue) {
        password.value = input
    }

    // Скрытие пароля
    fun onHideButtonClick() {
        _uiState.update { currentState ->
            currentState.copy(isHiddenPassword = !_uiState.value.isHiddenPassword)
        }
    }
}