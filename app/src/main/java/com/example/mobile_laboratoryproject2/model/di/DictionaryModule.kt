package com.example.mobile_laboratoryproject2.model.di

import com.example.mobile_laboratoryproject2.model.data.repositories.DictionaryApiService
import com.example.mobile_laboratoryproject2.model.data.repositories.DictionaryRepositoryImpl
import com.example.mobile_laboratoryproject2.model.domain.use_cases.dictionary_screen.DictionaryUseCase
import com.example.mobile_laboratoryproject2.model.domain.use_cases.dictionary_screen.IDictionaryRepository
import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.DictionaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val dictionaryModule = module {
    factory<DictionaryApiService> {
        val retrofit = get<Retrofit>()
        retrofit.create(DictionaryApiService::class.java)
    }

    factory<IDictionaryRepository> {
        DictionaryRepositoryImpl(get())
    }

    factory<DictionaryUseCase> {
        DictionaryUseCase(get())
    }

    viewModel<DictionaryViewModel> {
        DictionaryViewModel(get())
    }
}