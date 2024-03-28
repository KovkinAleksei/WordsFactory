package com.example.mobile_laboratoryproject2.model.domain.use_cases.dictionary_screen

import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.DictionaryMapper
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.database_entities.DefinitionEntity
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.usable_models.WordModel
import retrofit2.HttpException
import java.net.UnknownHostException

class DictionaryUseCase(
    private val dictionaryRepository: IDictionaryRepository
) {
    // Сохранение слова локально
    suspend fun addToDictionary(word: WordModel) {
        if (isInDictionary(word))
            return

        val wordEntity = DictionaryMapper.wordModelToWordEntity(word)
        dictionaryRepository.addToDictionary(wordEntity)

        addDefinitionsToDictionary(word)
    }

    // Проверка повторного добавления слова в словарь
    suspend fun isInDictionary(word: WordModel): Boolean {
        return dictionaryRepository.getWordId(word.word) != null
    }

    // Сохранение определений слова локально
    private suspend fun addDefinitionsToDictionary(word: WordModel) {
        val wordId = dictionaryRepository.getWordId(word.word)
        val definitionEntities = DictionaryMapper.wordModelToDefinitionEntities(word, wordId)

        dictionaryRepository.addDefinitions(definitionEntities)
    }

    // Получение значений слова
    suspend fun searchWord(word: String) : WordModel? {
        var wordModel: WordModel?

        try {
            wordModel = getWordFromServer(word)
        }
        catch (e: UnknownHostException) {
            wordModel = getWordFromDatabase(word)
        }

        return wordModel
    }

    // Получение слова запросом на сервер
    private suspend fun getWordFromServer(word: String): WordModel? {
        try {
            val record = dictionaryRepository.getDictionaryRecordFromServer(word.lowercase())[0]
            return DictionaryMapper.dictionaryRecordToWordModel(record)
        }
        catch (e: HttpException) {
            return null
        }
    }

    // Получение слова из бд
    private suspend fun getWordFromDatabase(word: String): WordModel? {
        val wordEntity = dictionaryRepository.getWordFromDatabase(word) ?: return null
        val wordDefinitions = getWordDefinitions(wordEntity.id!!)

        return DictionaryMapper.wordEntityToWordModel(wordEntity, wordDefinitions)
    }

    // Получение определений слова из бд
    private suspend fun getWordDefinitions(wordId: Int): List<DefinitionEntity> {
        return dictionaryRepository.getWordDefinitions(wordId)
    }

    // Удаление слова из словаря
    suspend fun removeWordFromDictionary(word: String) {
        dictionaryRepository.removeWord(word)
    }
}