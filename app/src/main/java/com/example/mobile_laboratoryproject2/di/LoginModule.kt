package com.example.mobile_laboratoryproject2.di

import com.example.mobile_laboratoryproject2.data.repositories.LoginRepositoryImpl
import com.example.mobile_laboratoryproject2.domain.use_cases.login_screen.ILoginRepository
import com.example.mobile_laboratoryproject2.domain.use_cases.login_screen.LoginUseCase
import com.example.mobile_laboratoryproject2.viewModel.login_screen.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    single<FirebaseAuth> {
        Firebase.auth
    }

    factory<ILoginRepository> {
        LoginRepositoryImpl(get())
    }

    factory<LoginUseCase> {
        LoginUseCase(get())
    }

    viewModel<LoginViewModel> {
        LoginViewModel(get())
    }
}