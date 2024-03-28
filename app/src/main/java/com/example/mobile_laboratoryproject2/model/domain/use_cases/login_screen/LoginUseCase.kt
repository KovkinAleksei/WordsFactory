package com.example.mobile_laboratoryproject2.model.domain.use_cases.login_screen

import android.util.Patterns
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.model.domain.entities.ValidationResult
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.tasks.await

class LoginUseCase(
    private val firebaseAuth: FirebaseAuth
) {
    // Авторизация
    suspend fun login(loginCredentials: LoginCredentials): ValidationResult {
        try{
            firebaseAuth.signInWithEmailAndPassword(loginCredentials.email, loginCredentials.password).await()
        }
        catch (e: FirebaseAuthInvalidCredentialsException){
            return ValidationResult(false, R.string.invalid_email_password)
        }
        catch (e: FirebaseNetworkException) {
            return ValidationResult(false, R.string.no_connection)
        }

        return ValidationResult(true, R.string.ok)
    }

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