package com.example.mobile_laboratoryproject2.model.domain.use_cases.login_screen

import android.util.Patterns
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.model.domain.entities.ValidationResult

class LoginUseCase {
    // Валидация email и пароля
    fun validateFieldValues(loginCredentials: LoginCredentials): ValidationResult {
        return  if (loginCredentials.email.isEmpty())
            ValidationResult(
                false,
                R.string.email_is_empty
            )
        else if (!Patterns.EMAIL_ADDRESS.matcher(loginCredentials.email).matches())
            ValidationResult(
                false,
                R.string.invalid_email
            )
        else if (loginCredentials.password.isEmpty())
            ValidationResult(
                false,
                R.string.password_is_empty
            )
        else
            ValidationResult(
                true,
                R.string.ok
            )
    }
}