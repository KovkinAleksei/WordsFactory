package com.example.mobile_laboratoryproject2.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
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
import com.example.mobile_laboratoryproject2.domain.use_cases.notification.AlarmScheduler
import com.example.mobile_laboratoryproject2.domain.use_cases.notification.NotificationUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    private val channelDescription = "Reminds to complete a test if users hasn't completed it in that day"
    private val channelName = "Reminder"

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

        val alarmScheduler = AlarmScheduler(this@App)
        alarmScheduler.schedule()
    }

    // Создание нотификационного канала
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NotificationUseCase.CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = channelDescription

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}