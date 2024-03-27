package com.example.mobile_laboratoryproject2.model.di

import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.DictionaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dictionaryModule = module {
    viewModel<DictionaryViewModel> {
        DictionaryViewModel()
    }
}