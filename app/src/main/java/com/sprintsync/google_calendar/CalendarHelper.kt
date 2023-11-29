package com.sprintsync.google_calendar

import android.accounts.Account
import android.content.Context
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.DateTime
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventDateTime
import com.google.api.services.calendar.model.EventReminder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.TimeZone

val zoneOffset = TimeZone.getDefault().rawOffset / 1000
val sign = if (zoneOffset >= 0) "+" else "-"
val hours = zoneOffset / 3600
val minutes = (zoneOffset % 3600) / 60
val timezone = "$sign%02d:%02d".format(hours, minutes)

fun triggerConsent(context: Context) {
	val calendar = getCalendar(context) ?: return

	calendar
		.calendars()
		.get("primary")
		.execute()
}

fun getCalendar(context: Context): Calendar? {
	val email = Firebase.auth.currentUser?.email ?: return null

	val credential = GoogleAccountCredential
		.usingOAuth2(context, listOf(CalendarScopes.CALENDAR))
		.setBackOff(ExponentialBackOff())
		.setSelectedAccount(Account(email, "com.google"))

	val transport = AndroidHttp.newCompatibleTransport()
	val jsonFactory = JacksonFactory.getDefaultInstance()
	return Calendar
		.Builder(transport, jsonFactory, credential)
		.setApplicationName("SprintSync")
		.build()
}

fun addCalendar(calendar: Calendar): String? {
	val calendarList = calendar
		.calendarList()
		.list()
		.execute()

	calendarList.items
		.find { it.summary == "SprintSync" }
		?.let { return it.id }

	val newCalendarContent = com.google.api.services.calendar.model.Calendar()
	newCalendarContent.summary = "SprintSync"
	newCalendarContent.timeZone = TimeZone.getDefault().id
	val newCalendar = calendar
		.calendars()
		.insert(newCalendarContent)
		.execute()

	return newCalendar.id
}

fun addEvent(
	context: Context,
	title: String? = null,
	description: String? = null,
	deadline: String
) {
	val calendar = getCalendar(context) ?: return

	val id = addCalendar(calendar)

	val event = Event()
		.setSummary(title)
		.setDescription(description)

	val startDateTime = DateTime("${deadline}T00:00:00$timezone")
	event.start = EventDateTime().setDateTime(startDateTime)

	val endDateTime = DateTime("${deadline}T23:59:59$timezone")
	event.end = EventDateTime().setDateTime(endDateTime)

	val reminder = EventReminder()
		.setMethod("popup")
		.setMinutes(3 * 24 * 60)

	event.reminders = Event
		.Reminders()
		.setUseDefault(false)
		.setOverrides(listOf(reminder))

	calendar
		.events()
		.insert(id, event)
		.execute()
}