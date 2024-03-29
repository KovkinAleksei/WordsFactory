package com.example.mobile_laboratoryproject2.domain.use_cases.dictionary_screen

import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.WordModel

interface IDictionaryRepository {
    suspend fun searchWord(word: String) : WordModel?
    suspend fun addToDictionary(word: WordModel)
    suspend fun getWordId(word: String) : Int?
    suspend fun removeWord(word: String)
}