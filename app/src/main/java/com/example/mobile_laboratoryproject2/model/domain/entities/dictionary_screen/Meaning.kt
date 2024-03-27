package com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen

import kotlinx.serialization.Serializable

@Serializable
data class Meaning(
    val partOfSpeech: String,
    val definitions: List<Definition>,
    val synonyms: List<String>,
    val antonyms: List<String>
)
