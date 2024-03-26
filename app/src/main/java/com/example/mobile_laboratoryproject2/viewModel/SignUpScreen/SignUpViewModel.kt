package com.example.mobile_laboratoryproject2.viewModel.SignUpScreen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_laboratoryproject2.model.domain.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
   // private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val signUpUseCase = SignUpUseCase()

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
        _uiState.update { currentState ->
            val validationResult = signUpUseCase.validateFieldValues(
                email.value.text,
                name.value.text,
                password.value.text
            )

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