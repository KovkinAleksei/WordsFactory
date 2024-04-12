package com.example.mobile_laboratoryproject2.di

import com.example.mobile_laboratoryproject2.viewModel.on_boarding_screen.OnBoardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val onBoardingModule = module {
    viewModel<OnBoardingViewModel> {
        OnBoardingViewModel(get())
    }
}