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
    override suspend fun getDictionaryRecordFromServer(word: String) : List<DictionaryRecord> {
        return dictionaryService.getDictionaryRecord(word)
    }

    // Сохранение слова локально
    override suspend fun addToDictionary(word: WordEntity) {
        val formatedWord = word.copy(word = word.word.lowercase())

        wordDao.addWord(formatedWord)
    }

    // Сохранение определений локально
    override suspend fun addDefinitions(definitions: List<DefinitionEntity>) {
        wordDao.addDefinitions(definitions = definitions)
    }

    // Получение id слова
    override suspend fun getWordId(word: String) : Int? {
        val formatedWord = word.lowercase()

        return wordDao.getWordId(formatedWord)
    }

    // Получение слова из бд
    override suspend fun getWordFromDatabase(word: String): WordEntity? {
        val formatedWord = word.lowercase()

        return wordDao.getWord(formatedWord)
    }

    // Получение определений слова из бд
    override suspend fun getWordDefinitions(wordId: Int): List<DefinitionEntity> {
        return wordDao.getWordDefinitions(wordId)
    }

    // Удаление слова из бд
    override suspend fun removeWord(word: String) {
        val formatedWord = word.lowercase()

        wordDao.removeWord(formatedWord)
    }
}