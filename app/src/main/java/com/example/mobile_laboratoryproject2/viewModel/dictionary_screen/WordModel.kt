package com.example.mobile_laboratoryproject2.viewModel.dictionary_screen

data class WordModel(
    val word: String,
    val phonetics: String?,
    val audio: String,
    val partOfSpeech: String,
    val audioSource: String?,
    val definitions: List<DefinitionModel>
)

