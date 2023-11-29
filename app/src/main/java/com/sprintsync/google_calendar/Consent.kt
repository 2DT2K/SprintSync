package com.sprintsync.google_calendar

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import com.google.firebase.messaging.FirebaseMessagingService
import com.sprintsync.data.view_models.MemberViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Consent() {
	val scope = rememberCoroutineScope()
	val context = LocalContext.current
	val sharedPref = context.getSharedPreferences("consent", FirebaseMessagingService.MODE_PRIVATE)
	val editor = sharedPref.edit()

	val memberVM = hiltViewModel<MemberViewModel>()

	LaunchedEffect(Unit) {
		val token = sharedPref.getString("token", null) ?: return@LaunchedEffect

		memberVM.addDevice(token)
		editor.remove("token")
		editor.apply()
	}

	val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
		if (it.resultCode == RESULT_OK) {
			editor.putBoolean("state", true)
			editor.apply()
		}
	}

	Box(contentAlignment = Alignment.Center) {
		Button(onClick = {
			scope.launch(Dispatchers.IO) {
				try {
					triggerConsent(context)
					editor.putBoolean("state", true)
					editor.apply()
				}
				catch (e: UserRecoverableAuthIOException) {
					launcher.launch(e.intent)
				}
			}
		}) {
			Text(text = "Consent")
		}
	}
}