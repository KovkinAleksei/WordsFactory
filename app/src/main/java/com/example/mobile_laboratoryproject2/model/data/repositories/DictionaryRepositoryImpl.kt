package com.example.mobile_laboratoryproject2.model.data.repositories

import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.DictionaryRecord
import com.example.mobile_laboratoryproject2.model.domain.use_cases.dictionary_screen.IDictionaryRepository

class DictionaryRepositoryImpl(
    private val dictionaryService: DictionaryApiService
) : IDictionaryRepository {
    override suspend fun getDictionaryRecord(word: String) : List<DictionaryRecord> {
        return dictionaryService.getDictionaryRecord(word)
    }
}