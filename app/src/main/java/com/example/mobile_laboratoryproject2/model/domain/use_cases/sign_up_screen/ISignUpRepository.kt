package com.example.mobile_laboratoryproject2.model.domain.use_cases.sign_up_screen

interface ISignUpRepository {
    suspend fun registerUser(user: UserDto)
    suspend fun isEmailTaken(email: String): Boolean
}