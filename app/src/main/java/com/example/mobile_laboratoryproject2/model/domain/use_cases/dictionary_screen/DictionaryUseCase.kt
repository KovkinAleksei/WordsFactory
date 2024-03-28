package com.example.mobile_laboratoryproject2.model.domain.use_cases.dictionary_screen

import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.DictionaryMapper
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.usable_models.WordModel

class DictionaryUseCase(
    private val dictionaryRepository: IDictionaryRepository
) {
    // Сохранение слова локально
    suspend fun addToDictionary(word: WordModel) {
        if (isAlreadyAdded(word))
            return

        val wordEntity = DictionaryMapper.wordModelToWordEntity(word)
        dictionaryRepository.addToDictionary(wordEntity)

        addDefinitionsToDictionary(word)
    }

    // Проверка повторного добавления слова в словарь
    private suspend fun isAlreadyAdded(word: WordModel): Boolean {
        return dictionaryRepository.getWordId(word.word) != null
    }

    // Сохранение определений слова локально
    private suspend fun addDefinitionsToDictionary(word: WordModel) {
        val wordId = dictionaryRepository.getWordId(word.word)
        val definitionEntities = DictionaryMapper.wordModelToDefinitionEntities(word, wordId)

        dictionaryRepository.addDefinitions(definitionEntities)
    }

    // Получение значений слова
    suspend fun getDictionaryRecord(word: String) : WordModel {
        var records = dictionaryRepository.getDictionaryRecord(word.lowercase())

        return DictionaryMapper.dictionaryRecordToWordModel(records[0])
    }
}