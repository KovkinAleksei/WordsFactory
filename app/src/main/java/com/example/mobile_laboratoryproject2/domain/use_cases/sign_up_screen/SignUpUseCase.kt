package com.example.mobile_laboratoryproject2.domain.use_cases.sign_up_screen

import android.util.Patterns
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.viewModel.ValidationResult
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.tasks.await

class SignUpUseCase(
    private val firebaseAuth: FirebaseAuth
) {
    // Регистрация пользователя
    suspend fun registerUser(user: UserDto): ValidationResult {
        try{
            firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()
        }
        catch (e: FirebaseAuthUserCollisionException) {
            return ValidationResult(false, R.string.email_is_taken)
        }
        catch (e: FirebaseAuthInvalidCredentialsException) {
            return ValidationResult(false, R.string.invalid_email)
        }
        catch (e: FirebaseNetworkException) {
            return ValidationResult(false, R.string.no_connection)
        }

        return ValidationResult(true, R.string.ok)
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