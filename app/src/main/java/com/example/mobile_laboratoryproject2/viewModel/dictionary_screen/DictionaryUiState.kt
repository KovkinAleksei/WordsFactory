package com.example.mobile_laboratoryproject2.viewModel.dictionary_screen

import com.example.mobile_laboratoryproject2.R

data class DictionaryUiState(
    val word: WordModel? = null,
    val isInDictionary: Boolean = false,
    val isWordCorrect: Boolean = true,
    val errorMessage: Int = R.string.ok
)
