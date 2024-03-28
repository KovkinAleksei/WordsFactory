package com.example.mobile_laboratoryproject2.model.domain.use_cases.dictionary_screen

import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.api_response.DictionaryRecord
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.database_entities.DefinitionEntity
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.database_entities.WordEntity

interface IDictionaryRepository {
    suspend fun getDictionaryRecord(word: String) : List<DictionaryRecord>
    suspend fun addToDictionary(word: WordEntity)
    suspend fun addDefinitions(definitions: List<DefinitionEntity>)
    suspend fun getWordId(word: String) : Int?
}