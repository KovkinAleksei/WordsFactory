package com.example.mobile_laboratoryproject2.di

import com.example.mobile_laboratoryproject2.domain.use_cases.sign_up_screen.SignUpUseCase
import com.example.mobile_laboratoryproject2.viewModel.sign_up_screen.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signUpModule = module {
    factory<SignUpUseCase> {
        SignUpUseCase(get())
    }

    viewModel<SignUpViewModel> {
        SignUpViewModel(get())
    }
}