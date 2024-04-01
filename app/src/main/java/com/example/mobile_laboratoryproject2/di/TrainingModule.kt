package com.example.mobile_laboratoryproject2.di

import com.example.mobile_laboratoryproject2.data.repositories.TrainingRepositoryImpl
import com.example.mobile_laboratoryproject2.domain.use_cases.training_screen.ITrainingRepository
import com.example.mobile_laboratoryproject2.domain.use_cases.training_screen.TrainingUseCase
import com.example.mobile_laboratoryproject2.viewModel.training_screen.TrainingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trainingModule = module {
    factory<ITrainingRepository> {
        TrainingRepositoryImpl(get())
    }

    factory<TrainingUseCase> {
        TrainingUseCase(get())
    }

    viewModel<TrainingViewModel>{
        TrainingViewModel(get())
    }
}