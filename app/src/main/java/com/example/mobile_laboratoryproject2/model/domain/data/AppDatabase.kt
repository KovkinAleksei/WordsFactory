package com.example.mobile_laboratoryproject2.model.domain.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobile_laboratoryproject2.model.domain.entities.UserEntity

@Database(
    version = 1,
    entities = [
        UserEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    companion object {
        fun createDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "WordsFactoryDb"
            ).build()
        }
    }
}