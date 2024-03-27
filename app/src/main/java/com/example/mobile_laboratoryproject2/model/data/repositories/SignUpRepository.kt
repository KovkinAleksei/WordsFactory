package com.example.mobile_laboratoryproject2.model.data.repositories

import com.example.mobile_laboratoryproject2.model.data.database.AppDatabase
import com.example.mobile_laboratoryproject2.model.domain.entities.UserEntity
import com.example.mobile_laboratoryproject2.model.domain.use_cases.sign_up_screen.UserDto

class SignUpRepository(
    private val database: AppDatabase
) {
    // Регистрация пользователя
    suspend fun registerUser(user: UserDto) {
        val userEntity = UserEntity(
            name = user.name,
            email = user.email,
            password = user.password
        )

        database.userDao.insertUser(userEntity)
    }

    // Проверка существования пользователя
    suspend fun isEmailTaken(email: String): Boolean {
        return database.userDao.isEmailTaken(email)
    }
}