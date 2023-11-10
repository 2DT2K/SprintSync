package com.sprintsync.auth

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
	private val _state = MutableStateFlow(AuthState())
	val state = _state.asStateFlow()

	fun update(newState: AuthState) = _state.update { newState }

	private fun reset() = _state.update { AuthState() }

	fun signUpWithPassword(
		scope: CoroutineScope,
		authenticator: Authenticator,
		email: String,
		password: String
	) {
		scope.launch {
			authenticator
				.signUp(email, password)
				.let { update(it) }
		}
	}

	fun signInWithPassword(
		scope: CoroutineScope,
		authenticator: Authenticator,
		email: String,
		password: String
	) {
		scope.launch {
			authenticator
				.signIn(email, password)
				.let { update(it) }
		}
	}

	fun resetPassword(
		scope: CoroutineScope,
		authenticator: Authenticator,
		email: String
	) {
		scope.launch { authenticator.resetPassword(email) }
	}

	fun signInWithGoogle(
		scope: CoroutineScope,
		authenticator: Authenticator,
		launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>
	) {
		scope.launch {
			authenticator
				.getSignInIntentSender()
				.let {
					launcher.launch(
						IntentSenderRequest
							.Builder(it ?: return@launch)
							.build()
					)
				}
		}
	}

	fun verifyEmail(
		scope: CoroutineScope,
		authenticator: Authenticator
	) {
		scope.launch { authenticator.verifyEmail() }
	}

	fun signOut(
		scope: CoroutineScope,
		authenticator: Authenticator
	) {
		scope.launch {
			authenticator.signOut()
			reset()
		}
	}
}