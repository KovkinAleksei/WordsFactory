package com.example.mobile_laboratoryproject2.data.local_data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobile_laboratoryproject2.domain.entities.UserEntity

@Dao
interface UserDao {
    // Добавление нового пользователя в таблицу
    @Insert
    suspend fun insertUser(user: UserEntity)

    // Нахождение существующего email
    @Query("SELECT EXISTS(SELECT * FROM Users WHERE email = :email)")
    suspend fun isEmailTaken(email: String) : Boolean

    // Авторизация пользователя
    @Query("SELECT id FROM Users WHERE email = :email AND password = :password")
    suspend fun login(email: String, password: String) : List<Int>
}