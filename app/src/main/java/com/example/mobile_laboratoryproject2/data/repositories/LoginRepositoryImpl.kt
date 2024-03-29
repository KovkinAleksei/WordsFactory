package com.example.mobile_laboratoryproject2.data.repositories

import com.example.mobile_laboratoryproject2.data.local_data_source.UserDao
import com.example.mobile_laboratoryproject2.domain.use_cases.login_screen.ILoginRepository
import com.example.mobile_laboratoryproject2.domain.use_cases.login_screen.LoginCredentials

class LoginRepositoryImpl(
    private val database: UserDao
): ILoginRepository {
    // Авторизация
    override suspend fun login(loginCredentials: LoginCredentials): List<Int> {
        return database.login(loginCredentials.email, loginCredentials.password)
    }
}