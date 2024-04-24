package com.example.mobile_laboratoryproject2.di

import com.example.mobile_laboratoryproject2.data.repositories.WidgetRepositoryImpl
import com.example.mobile_laboratoryproject2.domain.use_cases.widget.IWidgetRepository
import com.example.mobile_laboratoryproject2.domain.use_cases.widget.WidgetUseCase
import org.koin.dsl.module

val widgetModule = module {
    factory<IWidgetRepository> {
        WidgetRepositoryImpl(get())
    }

    factory<WidgetUseCase> {
        WidgetUseCase(get())
    }
}