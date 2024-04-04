package com.example.mobile_laboratoryproject2.data.local_data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mobile_laboratoryproject2.domain.entities.database_entities.DefinitionEntity
import com.example.mobile_laboratoryproject2.domain.entities.database_entities.WordEntity

@Dao
interface WordDao {
    // Сохранение слова локально
    @Insert
    suspend fun addWord(word: WordEntity)

    // Сохранение определений слов локально
    @Insert
    suspend fun addDefinitions(definitions: List<DefinitionEntity>)

    // Получение id слова
    @Query("SELECT id FROM words WHERE word = :word")
    suspend fun getWordId(word: String) : Int?

    // Получение слова
    @Query("SELECT * FROM words WHERE word = :word")
    suspend fun getWord(word: String) : WordEntity?

    // Получение определений слова
    @Query("SELECT * FROM definitions WHERE wordId = :wordId")
    suspend fun getWordDefinitions(wordId: Int): List<DefinitionEntity>

    // Удаление слова
    @Query("DELETE FROM words WHERE word = :word")
    suspend fun removeWord(word: String)

    // Получение кол-ва слов в словаре
    @Query("SELECT COUNT(*) FROM words")
    suspend fun getWordsAmount(): Int





   @Query("SELECT * FROM words ORDER BY learningCoefficient ASC LIMIT 10")
    suspend fun getTestAnswers(): List<WordEntity>

    @Query("SELECT * FROM definitions WHERE wordId = :wordId")
    suspend fun getDefinitions(wordId: Int): List<DefinitionEntity>

    @Query("SELECT * FROM words WHERE word <> :word ORDER BY RANDOM() LIMIT 2")
    suspend fun getAdditionalWords(word: String): List<WordEntity>

    // Сохранение слова локально
    @Update
    suspend fun updateWord(word: WordEntity)
}