package com.sprintsync.google_calendar

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Build
import android.os.PersistableBundle
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

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

		val jobService = ComponentName(this, BackgroundService::class.java)
		val extras = PersistableBundle().apply {
			putString("title", "New task: ${data["taskName"]}")
			putString("body", data["taskDescription"])
			putString("taskName", data["taskName"])
			putString("taskDescription", data["taskDescription"])
			putString("taskDeadline", data["taskDeadline"])
		}
		val jobBuilder = JobInfo
			.Builder(1, jobService)
			.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
			.setBackoffCriteria(15000, JobInfo.BACKOFF_POLICY_LINEAR)
			.setExtras(extras)
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) jobBuilder.setExpedited(true)
		val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
		jobScheduler.schedule(jobBuilder.build())
	}
}