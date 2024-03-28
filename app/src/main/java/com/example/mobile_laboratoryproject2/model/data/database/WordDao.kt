package com.example.mobile_laboratoryproject2.model.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.database_entities.DefinitionEntity
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.database_entities.WordEntity

@Dao
interface WordDao {
    // Сохранение слова локально
    @Insert
    suspend fun addWord(word: WordEntity)

    // Сохранение определений слов локально
    @Insert
    suspend fun addDefinitions(definitions: List<DefinitionEntity>)

    // Получение id слова в бд
    @Query("SELECT id FROM words WHERE word = :word")
    suspend fun getWordId(word: String) : Int?
}