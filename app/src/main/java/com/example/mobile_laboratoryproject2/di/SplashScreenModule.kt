package com.example.mobile_laboratoryproject2.di

import com.example.mobile_laboratoryproject2.viewModel.splash_screen.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashScreenModule = module {
    viewModel<SplashScreenViewModel>{
        SplashScreenViewModel(get())
    }
}