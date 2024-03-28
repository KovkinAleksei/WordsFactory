package com.example.mobile_laboratoryproject2.model.data.repositories

import com.example.mobile_laboratoryproject2.model.data.database.WordDao
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.api_response.DictionaryRecord
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.database_entities.DefinitionEntity
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.database_entities.WordEntity
import com.example.mobile_laboratoryproject2.model.domain.use_cases.dictionary_screen.IDictionaryRepository

class DictionaryRepositoryImpl(
    private val dictionaryService: DictionaryApiService,
    private val wordDao: WordDao
) : IDictionaryRepository {
    // Получение списка значений слова
    override suspend fun getDictionaryRecord(word: String) : List<DictionaryRecord> {
        return dictionaryService.getDictionaryRecord(word)
    }

    // Сохранение слова локально
    override suspend fun addToDictionary(word: WordEntity) {
        wordDao.addWord(word)
    }

    // Сохранение определений локально
    override suspend fun addDefinitions(definitions: List<DefinitionEntity>) {
        wordDao.addDefinitions(definitions = definitions)
    }

    // Получение id слова
    override suspend fun getWordId(word: String) : Int? {
        return wordDao.getWordId(word)
    }
}