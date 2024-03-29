package com.example.mobile_laboratoryproject2.data.repositories

import com.example.mobile_laboratoryproject2.data.local_data_source.UserDao
import com.example.mobile_laboratoryproject2.domain.entities.UserEntity
import com.example.mobile_laboratoryproject2.domain.use_cases.sign_up_screen.ISignUpRepository
import com.example.mobile_laboratoryproject2.domain.use_cases.sign_up_screen.UserDto

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