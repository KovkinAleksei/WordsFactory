package com.example.mobile_laboratoryproject2.model.domain.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobile_laboratoryproject2.model.domain.entities.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT EXISTS(SELECT * FROM Users WHERE email = :userEmail)")
    suspend fun isEmailTaken(userEmail: String) : Boolean
}