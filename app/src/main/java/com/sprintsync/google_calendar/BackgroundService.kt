package com.sprintsync.google_calendar

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.job.JobParameters
import android.app.job.JobService
import androidx.core.app.NotificationCompat
import com.sprintsync.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class BackgroundService : JobService() {
	override fun onStartJob(params: JobParameters?): Boolean {
		val extras = params?.extras

		val notification = NotificationCompat
			.Builder(this, "SprintSync")
			.setSmallIcon(R.drawable.notification)
			.setContentTitle(extras?.getString("title"))
			.setContentText(extras?.getString("body"))
			.setAutoCancel(true)
			.build()

		val channel = NotificationChannel("SprintSync", "SprintSync", IMPORTANCE_HIGH)
		(getSystemService(NOTIFICATION_SERVICE) as NotificationManager).apply {
			createNotificationChannel(channel)
			notify(Random.nextInt(), notification)
		}


		val sharedPref = getSharedPreferences("consent", MODE_PRIVATE)
		if (!sharedPref.getBoolean("state", false)) return true

		val title = extras?.getString("taskName")
		val description = extras?.getString("taskDescription")
		val deadline = extras?.getString("taskDeadline")

		if (deadline.isNullOrEmpty()) return true

		CoroutineScope(Dispatchers.IO).launch {
			addEvent(applicationContext, title, description, deadline.substring(0, 10))
		}

		return true
	}

	override fun onStopJob(params: JobParameters?) = true
}