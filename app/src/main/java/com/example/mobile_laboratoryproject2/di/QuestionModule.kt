package com.example.mobile_laboratoryproject2.di

import com.example.mobile_laboratoryproject2.data.repositories.QuestionRepositoryImpl
import com.example.mobile_laboratoryproject2.domain.use_cases.question_screen.IQuestionRepository
import com.example.mobile_laboratoryproject2.domain.use_cases.question_screen.QuestionUseCase
import com.example.mobile_laboratoryproject2.viewModel.question_screen.QuestionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val questionModule = module {

    factory<IQuestionRepository> {
        QuestionRepositoryImpl(get())
    }

    factory<QuestionUseCase> {
        QuestionUseCase(get())
    }

    viewModel<QuestionViewModel>{
        QuestionViewModel(get())
    }
}