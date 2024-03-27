package com.example.mobile_laboratoryproject2.model.di

import com.example.mobile_laboratoryproject2.model.domain.use_cases.login_screen.LoginUseCase
import com.example.mobile_laboratoryproject2.viewModel.login_screen.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    factory<LoginUseCase> {
        LoginUseCase()
    }

    viewModel<LoginViewModel> {
        LoginViewModel(get())
    }
}