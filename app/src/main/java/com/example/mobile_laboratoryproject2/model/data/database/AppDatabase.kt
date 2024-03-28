package com.example.mobile_laboratoryproject2.model.data.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobile_laboratoryproject2.model.domain.entities.UserEntity
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.database_entities.DefinitionEntity
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.database_entities.WordEntity

@Database(
    version = 2,
    entities = [
        UserEntity::class,
        WordEntity::class,
        DefinitionEntity::class
    ],
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val wordDao: WordDao

    companion object {
        fun createDatabase(context: Context): AppDatabase {
            return Room
                .databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "WordsFactoryDb"
                )
                .addMigrations(firstMigration)
                .build()
        }
    }
}