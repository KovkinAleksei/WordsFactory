package com.example.mobile_laboratoryproject2.domain.use_cases.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.mobile_laboratoryproject2.R
import com.example.mobile_laboratoryproject2.data.local_data_source.TestPreferencesStore
import com.example.mobile_laboratoryproject2.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate

class NotificationUseCase(
    private val context: Context
) : KoinComponent {
    companion object {
        const val CHANNEL_ID = "WordsFactoryNotificationChannel"
    }

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    private val testPrefs: TestPreferencesStore by inject<TestPreferencesStore>()
    private val notificationTitle = "Reminder"
    private val notificationText = "Don't forget to complete a test today"

    // Отображение уведомления
    fun showNotification() {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            0,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(activityPendingIntent)
            .setAutoCancel(true)
            .build()

        var isNotified = false

        CoroutineScope(Dispatchers.IO).launch {
            testPrefs.store.data.collectLatest {
                if (it.lastCompletedDate != LocalDate.now().toString() && !isNotified) {
                    notificationManager.notify(0, notification)
                    isNotified = true
                }
            }
        }
    }
}