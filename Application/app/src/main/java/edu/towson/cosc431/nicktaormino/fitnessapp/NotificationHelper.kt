package edu.towson.cosc431.nicktaormino.fitnessapp
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService

fun createNotificationChannel(ctx: Context, notificationId: String) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "SERVICE_DEMO_CHANNEL"
        val descriptionText = "Notification channel for ServiceDemo"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(notificationId, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager =
            getSystemService(ctx, NotificationManager::class.java)
        notificationManager?.createNotificationChannel(channel)
    }
}

