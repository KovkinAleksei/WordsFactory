package com.example.mobile_laboratoryproject2.viewModel.login_screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_laboratoryproject2.model.domain.entities.ValidationResult
import com.example.mobile_laboratoryproject2.model.domain.use_cases.login_screen.LoginCredentials
import com.example.mobile_laboratoryproject2.model.domain.use_cases.login_screen.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    val email = mutableStateOf(TextFieldValue(""))
    val password = mutableStateOf(TextFieldValue(""))

    // Ввод email пользователя
    fun handleEmailInput(input: TextFieldValue) {
        email.value = input.copy(input.text.replace(" ", ""))
    }

    // Ввод пароля пользователя
    fun handlePasswordInput(input: TextFieldValue) {
        password.value = input.copy(input.text.replace(" ", ""))
    }

    // Скрытие пароля
    fun onHideButtonClick() {
        _uiState.update { currentState ->
            currentState.copy(isHiddenPassword = !_uiState.value.isHiddenPassword)
        }
    }

    // Авторизация
    fun onSignInButtonClick() {
        val loginCredentials = LoginCredentials(
            email = email.value.text,
            password = password.value.text
        )

        updateValidationState(loginUseCase.validateFieldValues(loginCredentials))

        viewModelScope.launch(Dispatchers.IO) {
            if (_uiState.value.areFieldValuesCorrect)
                updateValidationState(loginUseCase.login(loginCredentials))
        }
    }

    // Получение сообщения об ошибке
    private fun updateValidationState(validationResult: ValidationResult) {
        _uiState.update { currentState ->
            currentState.copy(
                areFieldValuesCorrect = validationResult.isCorrect,
                errorMessage = validationResult.errorMessage
            )
        }
    }

    // Закрытие диалогового окна
    fun onDismiss() {
        _uiState.update { currentState ->
            currentState.copy(areFieldValuesCorrect = true)
        }
    }
}