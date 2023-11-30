package com.sprintsync.google_calendar

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sprintsync.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class MessageService : FirebaseMessagingService() {
	override fun onNewToken(token: String) {
		super.onNewToken(token)

		val editor = getSharedPreferences("device", MODE_PRIVATE).edit()
		editor.putString("token", token)
		editor.apply()
	}

	override fun onMessageReceived(message: RemoteMessage) {
		super.onMessageReceived(message)

		val data = message.data

		val notification = NotificationCompat
			.Builder(this, "SprintSync")
			.setSmallIcon(R.drawable.notification)
			.setContentTitle("New task: ${data["taskName"]}")
			.setContentText(data["taskDescription"])
			.setAutoCancel(true)
			.build()

		val channel = NotificationChannel("SprintSync", "SprintSync", IMPORTANCE_HIGH)
		(getSystemService(NOTIFICATION_SERVICE) as NotificationManager).apply {
			createNotificationChannel(channel)
			notify(Random.nextInt(), notification)
		}

		val title = data["taskName"]
		val description = data["taskDescription"]
		val deadline = data["taskDeadline"]

		if (deadline.isNullOrEmpty()) return

		CoroutineScope(Dispatchers.IO).launch {
			try {
				addEvent(applicationContext, title, description, deadline.substring(0, 10))
			}
			catch (_: Exception) {
			}
		}
	}
}