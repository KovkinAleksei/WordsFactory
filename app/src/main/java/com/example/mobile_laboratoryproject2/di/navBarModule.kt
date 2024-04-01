package com.example.mobile_laboratoryproject2.di

import com.example.mobile_laboratoryproject2.viewModel.common.NavBarViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val navBarModule = module {
    viewModel<NavBarViewModel> {
        NavBarViewModel()
    }
}