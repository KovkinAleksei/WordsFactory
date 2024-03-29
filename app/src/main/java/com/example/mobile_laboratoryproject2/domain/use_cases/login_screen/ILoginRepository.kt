package com.example.mobile_laboratoryproject2.domain.use_cases.login_screen

interface ILoginRepository {
    suspend fun login(loginCredentials: LoginCredentials): List<Int>
}