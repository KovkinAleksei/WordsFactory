package com.example.mobile_laboratoryproject2.model.domain.use_cases.dictionary_screen

import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.DictionaryRecord

class DictionaryUseCase(
    private val dictionaryRepository: IDictionaryRepository
) {
    suspend fun getDictionaryRecord(word: String) : List<DictionaryRecord> {
        return dictionaryRepository.getDictionaryRecord(word.lowercase())
    }
}