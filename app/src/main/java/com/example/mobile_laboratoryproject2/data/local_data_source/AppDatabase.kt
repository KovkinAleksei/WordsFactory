package com.example.mobile_laboratoryproject2.data.local_data_source

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobile_laboratoryproject2.domain.entities.database_entities.DefinitionEntity
import com.example.mobile_laboratoryproject2.domain.entities.database_entities.WordEntity

@Database(
    version = 4,
    entities = [
        WordEntity::class,
        DefinitionEntity::class
    ],
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract val dictionaryDao: DictionaryDao
    abstract val trainingDao: TrainingDao
    abstract val questionDao: QuestionDao

    companion object {
        fun createDatabase(context: Context): AppDatabase {
            return Room
                .databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "WordsFactoryDb"
                )
                .addMigrations(firstMigration, secondMigration, thirdMigration)
                .build()
        }
    }
}