package com.example.mobile_laboratoryproject2.di

import com.example.mobile_laboratoryproject2.data.network_data_source.DictionaryApiService
import com.example.mobile_laboratoryproject2.data.repositories.DictionaryRepositoryImpl
import com.example.mobile_laboratoryproject2.domain.use_cases.dictionary_screen.DictionaryUseCase
import com.example.mobile_laboratoryproject2.domain.use_cases.dictionary_screen.IDictionaryRepository
import com.example.mobile_laboratoryproject2.viewModel.dictionary_screen.DictionaryViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val dictionaryModule = module {
    factory<DictionaryApiService> {
        val retrofit = get<Retrofit>()
        retrofit.create(DictionaryApiService::class.java)
    }

    factory<IDictionaryRepository> {
        DictionaryRepositoryImpl(get(), get())
    }

    factory<DictionaryUseCase> {
        DictionaryUseCase(get(), get(), androidContext())
    }

    viewModel<DictionaryViewModel> {
        DictionaryViewModel(get())
    }
}