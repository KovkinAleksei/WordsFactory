package com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.api_response

import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.api_response.Definition
import kotlinx.serialization.Serializable

@Serializable
data class Meaning(
    var partOfSpeech: String,
    val definitions: List<Definition>,
    val synonyms: List<String>,
    val antonyms: List<String>
)
