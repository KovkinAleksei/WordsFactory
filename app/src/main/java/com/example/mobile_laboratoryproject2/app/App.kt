package com.example.mobile_laboratoryproject2.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.datastore.preferences.preferencesDataStore
import com.example.mobile_laboratoryproject2.di.authorizationModule
import com.example.mobile_laboratoryproject2.di.databaseModule
import com.example.mobile_laboratoryproject2.di.dictionaryModule
import com.example.mobile_laboratoryproject2.di.loginModule
import com.example.mobile_laboratoryproject2.di.networkModule
import com.example.mobile_laboratoryproject2.di.notificationModule
import com.example.mobile_laboratoryproject2.di.onBoardingModule
import com.example.mobile_laboratoryproject2.di.questionModule
import com.example.mobile_laboratoryproject2.di.signUpModule
import com.example.mobile_laboratoryproject2.di.splashScreenModule
import com.example.mobile_laboratoryproject2.di.trainingModule
import com.example.mobile_laboratoryproject2.di.videoModule
import com.example.mobile_laboratoryproject2.di.widgetModule
import com.example.mobile_laboratoryproject2.domain.use_cases.notification.NotificationUseCase
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
                    trainingModule,
                    questionModule,
                    videoModule,
                    splashScreenModule,
                    widgetModule,
                    notificationModule
                )
            )
        }

        createNotificationChannel()
    }

    // Создание нотификационного канала
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NotificationUseCase.CHANNEL_ID,
                "Reminds",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Reminds to complete a test if users hasn't completed it in that day"

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}