package com.example.mobile_laboratoryproject2.di

import com.example.mobile_laboratoryproject2.domain.use_cases.notification.NotificationUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val notificationModule = module {
    factory<NotificationUseCase> {
        NotificationUseCase(androidContext())
    }
}