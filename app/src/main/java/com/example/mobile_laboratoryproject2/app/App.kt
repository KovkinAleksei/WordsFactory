package com.example.mobile_laboratoryproject2.app

import android.app.Application
import com.example.mobile_laboratoryproject2.di.authorizationModule
import com.example.mobile_laboratoryproject2.di.databaseModule
import com.example.mobile_laboratoryproject2.di.dictionaryModule
import com.example.mobile_laboratoryproject2.di.loginModule
import com.example.mobile_laboratoryproject2.di.networkModule
import com.example.mobile_laboratoryproject2.di.onBoardingModule
import com.example.mobile_laboratoryproject2.di.signUpModule
import com.example.mobile_laboratoryproject2.di.trainingModule
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
                    dictionaryModule,
                    databaseModule,
                    networkModule,
                    authorizationModule,
                    trainingModule
                )
            )
        }
    }
}