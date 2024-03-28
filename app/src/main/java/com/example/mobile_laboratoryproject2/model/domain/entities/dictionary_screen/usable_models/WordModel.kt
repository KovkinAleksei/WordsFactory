package com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.usable_models

data class WordModel(
    val word: String,
    val phonetics: String?,
    val audio: String,
    val partOfSpeech: String,
    val audioSource: String?,
    val definitions: List<DefinitionModel>
)

