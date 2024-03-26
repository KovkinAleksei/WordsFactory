package com.example.mobile_laboratoryproject2.model.di

import com.example.mobile_laboratoryproject2.model.domain.SignUpUseCase
import com.example.mobile_laboratoryproject2.viewModel.on_boarding_screen.OnBoardingViewModel
import com.example.mobile_laboratoryproject2.viewModel.sign_up_screen.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signUpModule = module {
    factory<SignUpUseCase> {
        SignUpUseCase()
    }

    viewModel<OnBoardingViewModel> {
        OnBoardingViewModel()
    }

    viewModel<SignUpViewModel> {
        SignUpViewModel(signUpUseCase = get())
    }
}