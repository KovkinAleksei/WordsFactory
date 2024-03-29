package com.example.mobile_laboratoryproject2.domain.use_cases.dictionary_screen

import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.viewModel.ValidationResult
import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.WordModel

class DictionaryUseCase(
    private val dictionaryRepository: IDictionaryRepository
) {
    // Валидация поискового запроса
    fun validateSearchTextField(word: String): ValidationResult {
        return if (word.trim().isEmpty())
            ValidationResult(false, R.string.empty_search)
        else
            ValidationResult(true, R.string.ok)
    }

    // Сохранение слова локально
    suspend fun addToDictionary(word: WordModel) {
        if (isInDictionary(word))
            return

        dictionaryRepository.addToDictionary(word)
    }

    // Проверка повторного добавления слова в словарь
    suspend fun isInDictionary(word: WordModel): Boolean {
        return dictionaryRepository.getWordId(word.word) != null
    }

    // Получение значений слова
    suspend fun searchWord(word: String): WordModel? {
        return dictionaryRepository.searchWord(word)
    }

    // Удаление слова из словаря
    suspend fun removeWordFromDictionary(word: String) {
        dictionaryRepository.removeWord(word)
    }
}