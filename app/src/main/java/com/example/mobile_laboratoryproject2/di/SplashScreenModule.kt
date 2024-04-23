package com.example.mobile_laboratoryproject2.di

import com.example.mobile_laboratoryproject2.data.repositories.SplashRepositoryImpl
import com.example.mobile_laboratoryproject2.domain.use_cases.splash_screen.ISplashRepository
import com.example.mobile_laboratoryproject2.domain.use_cases.splash_screen.SplashUseCase
import com.example.mobile_laboratoryproject2.viewModel.splash_screen.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashScreenModule = module {

    factory<ISplashRepository> {
        SplashRepositoryImpl(get())
    }

    factory<SplashUseCase> {
        SplashUseCase(get())
    }

    viewModel<SplashScreenViewModel>{
        SplashScreenViewModel(get())
    }
}