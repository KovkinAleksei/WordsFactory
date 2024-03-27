package com.example.mobile_laboratoryproject2.app

import android.app.Application
import com.example.mobile_laboratoryproject2.model.di.dictionaryModule
import com.example.mobile_laboratoryproject2.model.di.loginModule
import com.example.mobile_laboratoryproject2.model.di.onBoardingModule
import com.example.mobile_laboratoryproject2.model.di.signUpModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    signUpModule,
                    onBoardingModule,
                    loginModule,
                    dictionaryModule
                )
            )
        }
    }
}