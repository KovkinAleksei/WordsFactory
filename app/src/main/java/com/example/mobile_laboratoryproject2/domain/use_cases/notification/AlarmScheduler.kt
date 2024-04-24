package com.example.mobile_laboratoryproject2.domain.use_cases.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

class AlarmScheduler(
    private val context: Context
) {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    fun schedule() {
        val intent = Intent(context, AlarmReceiver::class.java)

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT.plusHours(20))
                .atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            AlarmManager.INTERVAL_DAY,
            PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}