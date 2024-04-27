package com.example.mobile_laboratoryproject2.domain.use_cases.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AlarmReceiver : KoinComponent, BroadcastReceiver() {
    private val notificationUseCase: NotificationUseCase by inject<NotificationUseCase>()

    override fun onReceive(context: Context?, intent: Intent?) {
        notificationUseCase.showNotification()
    }
}