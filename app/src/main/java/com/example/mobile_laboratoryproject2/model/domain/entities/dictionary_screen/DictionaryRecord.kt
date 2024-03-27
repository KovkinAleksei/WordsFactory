package com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen

import kotlinx.serialization.Serializable

@Serializable
data class DictionaryRecord(
    val word: String,
    val phonetic: String,
    val phonetics: List<Phonetics>,
    val meanings: List<Meaning>,
    val license: License,
    val sourceUrls: List<String>
)
