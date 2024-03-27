package com.example.mobile_laboratoryproject2.model.domain.use_cases.sign_up_screen

import android.util.Patterns
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.model.domain.entities.ValidationResult
import com.example.mobile_laboratoryproject2.model.data.repositories.SignUpRepositoryImpl

class SignUpUseCase(
    private val signUpRepositoryImpl: ISignUpRepository
) {
    // Регистрация пользователя
    suspend fun registerUser(user: UserDto): ValidationResult {
        val isEmailTaken = signUpRepositoryImpl.isEmailTaken(user.email)

        if (!isEmailTaken) {
            signUpRepositoryImpl.registerUser(user)
            return ValidationResult(true, R.string.ok)
        }

        return ValidationResult(false, R.string.email_is_taken)
    }

    // Валидация email, имени и пароля
    fun validateFieldValues(user: UserDto): ValidationResult {
        return if (user.name.trim().isEmpty())
            ValidationResult(
                false,
                R.string.name_is_empty
            )
        else if (user.email.isEmpty())
            ValidationResult(
                false,
                R.string.email_is_empty
            )
        else if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches())
            ValidationResult(
                false,
                R.string.invalid_email
            )
        else if (user.password.isEmpty())
            ValidationResult(
                false,
                R.string.password_is_empty
            )
        else if (user.password.length < 6)
            ValidationResult(
                false,
                R.string.password_is_short
            )
        else
            ValidationResult(
                true,
                R.string.ok
            )
    }
}