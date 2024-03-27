package com.example.mobile_laboratoryproject2.model.di

import com.example.mobile_laboratoryproject2.model.data.database.AppDatabase
import com.example.mobile_laboratoryproject2.model.data.database.UserDao
import com.example.mobile_laboratoryproject2.model.data.repositories.SignUpRepositoryImpl
import com.example.mobile_laboratoryproject2.model.domain.use_cases.sign_up_screen.ISignUpRepository
import com.example.mobile_laboratoryproject2.model.domain.use_cases.sign_up_screen.SignUpUseCase
import com.example.mobile_laboratoryproject2.viewModel.sign_up_screen.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signUpModule = module {
    factory<ISignUpRepository> {
        SignUpRepositoryImpl(get())
    }

    factory<SignUpUseCase> {
        SignUpUseCase(get())
    }

    viewModel<SignUpViewModel> {
        SignUpViewModel(get())
    }
}