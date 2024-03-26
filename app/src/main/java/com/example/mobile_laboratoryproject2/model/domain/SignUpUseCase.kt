package com.example.mobile_laboratoryproject2.model.domain

import android.util.Patterns
import com.example.mobile_laboratoryproject2.R

class SignUpUseCase {
    // Валидация email, имени и пароля
    fun validateFieldValues(email: String, name: String, password: String): ValidationResult {
        return if (name.trim().isEmpty())
            ValidationResult(
                false,
                R.string.name_is_empty
            )
        else if (email.isEmpty())
            ValidationResult(
                false,
                R.string.email_is_empty
            )
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            ValidationResult(
                false,
                R.string.invalid_email
            )
        else if (password.isEmpty())
            ValidationResult(
                false,
                R.string.password_is_empty
            )
        else if (password.length < 6)
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