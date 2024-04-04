package com.example.mobile_laboratoryproject2.di

import com.example.mobile_laboratoryproject2.viewModel.question_screen.QuestionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val questionModule = module {
    viewModel<QuestionViewModel>{
        QuestionViewModel()
    }
}