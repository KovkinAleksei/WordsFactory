package com.example.mobile_laboratoryproject2.viewModel.dictionary_screen

import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.model.domain.entities.dictionary_screen.usable_models.WordModel

data class DictionaryUiState(
    val word: WordModel? = null,
    val isInDictionary: Boolean = false,
    val isNotFound: Boolean = false,
    val errorMessage: Int = R.string.ok
)
