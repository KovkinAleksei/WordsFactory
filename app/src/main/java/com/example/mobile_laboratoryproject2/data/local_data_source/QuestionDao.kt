package com.example.mobile_laboratoryproject2.data.local_data_source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.mobile_laboratoryproject2.domain.entities.database_entities.DefinitionEntity
import com.example.mobile_laboratoryproject2.domain.entities.database_entities.WordEntity

@Dao
interface QuestionDao {
    // Получение слова
    @Query("SELECT * FROM words WHERE word = :word")
    suspend fun getWord(word: String) : WordEntity?

    // Получение ответов для теста
    @Query("SELECT * FROM words ORDER BY learningCoefficient ASC LIMIT 10")
    suspend fun getTestAnswers(): List<WordEntity>

    // Получение определения слова
    @Query("SELECT * FROM definitions WHERE wordId = :wordId")
    suspend fun getDefinitions(wordId: Int): List<DefinitionEntity>

    // Получение дополнительных вариантов ответа в тесте
    @Query("SELECT * FROM words WHERE word <> :word ORDER BY RANDOM() LIMIT 2")
    suspend fun getAdditionalWords(word: String): List<WordEntity>

    // Обновление слова
    @Update
    suspend fun updateWord(word: WordEntity)
}