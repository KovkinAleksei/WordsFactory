package com.example.mobile_laboratoryproject2.model.data.repositories

import com.example.mobile_laboratoryproject2.model.data.database.UserDao
import com.example.mobile_laboratoryproject2.model.domain.use_cases.login_screen.ILoginRepository
import com.example.mobile_laboratoryproject2.model.domain.use_cases.login_screen.LoginCredentials

class LoginRepositoryImpl(
    private val database: UserDao
): ILoginRepository {
    // Авторизация
    override suspend fun login(loginCredentials: LoginCredentials): List<Int> {
        return database.login(loginCredentials.email, loginCredentials.password)
    }
}