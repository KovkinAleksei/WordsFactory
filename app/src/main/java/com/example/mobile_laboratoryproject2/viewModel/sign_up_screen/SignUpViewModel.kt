package com.example.mobile_laboratoryproject2.viewModel.sign_up_screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_laboratoryproject2.model.domain.entities.ValidationResult
import com.example.mobile_laboratoryproject2.model.domain.use_cases.SignUpUseCase
import com.example.mobile_laboratoryproject2.model.domain.use_cases.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
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

    // Регистрация
    fun onSignUpButtonClick() {
        val user = UserDto(
            name.value.text,
            email.value.text,
            password.value.text
        )

        // Валидация введённых полей
        updateValidationState(signUpUseCase.validateFieldValues(user))

        // Регистрация пользователя
        viewModelScope.launch(Dispatchers.IO) {
            if (_uiState.value.areFieldValuesCorrect){
                updateValidationState(signUpUseCase.registerUser(user))
            }
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