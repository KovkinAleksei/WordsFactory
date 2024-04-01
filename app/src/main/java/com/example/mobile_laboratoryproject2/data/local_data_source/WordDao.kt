package com.example.mobile_laboratoryproject2.data.local_data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
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
}