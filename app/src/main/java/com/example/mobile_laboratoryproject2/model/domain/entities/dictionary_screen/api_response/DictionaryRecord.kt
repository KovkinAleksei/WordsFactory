package com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.api_response

import kotlinx.serialization.Serializable

@Serializable
data class DictionaryRecord(
    var word: String,
    val phonetic: String? = null,
    val phonetics: List<Phonetics>,
    val meanings: List<Meaning>,
    val license: License,
    val sourceUrls: List<String>,
    val learningCoefficient: Int = 0
)
