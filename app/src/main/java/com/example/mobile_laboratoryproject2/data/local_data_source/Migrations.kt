package com.example.mobile_laboratoryproject2.data.local_data_source

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val firstMigration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE words (" +
                    "id INTEGER, " +
                    " word TEXT NOT NULL, " +
                    "phonetics TEXT, " +
                    "audio TEXT NOT NULL, " +
                    "partOfSpeech TEXT NOT NULL, " +
                    "audioSource TEXT, " +
                    "PRIMARY KEY(id));"
        )

        database.execSQL(
            "CREATE TABLE definitions (" +
                    "definition TEXT NOT NULL, " +
                    "id INTEGER PRIMARY KEY, " +
                    "example TEXT, " +
                    "wordId INTEGER, " +
                    "FOREIGN KEY(wordId) REFERENCES words(id) ON DELETE CASCADE);"
        )

        database.execSQL("CREATE INDEX index_definitions_wordId ON definitions (wordId);")
    }
}

val secondMigration = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE words ADD COLUMN learningCoefficient INTEGER NOT NULL DEFAULT 0;")
    }
}

var thirdMigration = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("DROP TABLE Users;")
    }
}