package com.example.mobile_laboratoryproject2.model.domain.use_cases.dictionary_screen

import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.Definition
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.DictionaryRecord

class DictionaryUseCase(
    private val dictionaryRepository: IDictionaryRepository
) {
    // Получение всех определений слова
    fun getAllDefinitions(word: DictionaryRecord): List<Definition> {
        val definitions = mutableListOf<Definition>()

        word.meanings.forEach {
            definitions.addAll(it.definitions)
        }

        return definitions
    }

    suspend fun getDictionaryRecord(word: String) : List<DictionaryRecord> {
        var records = dictionaryRepository.getDictionaryRecord(word.lowercase())
        records[0].word = records[0].word.replaceFirstChar { it.uppercase() }
        records[0].meanings[0].partOfSpeech = records[0].meanings[0].partOfSpeech.replaceFirstChar { it.uppercase() }

        return records
    }
}