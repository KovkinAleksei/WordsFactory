package com.example.mobile_laboratoryproject2.model.data.repositories

import com.example.mobile_laboratoryproject2.model.data.database.UserDao
import com.example.mobile_laboratoryproject2.model.domain.entities.UserEntity
import com.example.mobile_laboratoryproject2.model.domain.use_cases.sign_up_screen.ISignUpRepository
import com.example.mobile_laboratoryproject2.model.domain.use_cases.sign_up_screen.UserDto

class SignUpRepositoryImpl(
    private val database: UserDao
): ISignUpRepository {
    // Регистрация пользователя
    override suspend fun registerUser(user: UserDto) {
        val userEntity = UserEntity(
            name = user.name,
            email = user.email,
            password = user.password
        )

        database.insertUser(userEntity)
    }

    // Проверка существования пользователя
    override suspend fun isEmailTaken(email: String): Boolean {
        return database.isEmailTaken(email)
    }
}