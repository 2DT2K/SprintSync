package com.sprintsync.auth

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthState(
	val signedIn: Boolean = false,
	val errorCode: String? = null,
	val errorMessage: String? = null,
)

@HiltViewModel
class AuthViewModel @Inject constructor(
	private val authenticator: Authenticator
) : ViewModel() {
	private val scope = viewModelScope
	private val _state = MutableStateFlow(AuthState(Authenticator.isSignedIn))
	val state = _state.asStateFlow()

	private fun update(newState: AuthState) = _state.update { newState }

	fun signUp(email: String, password: String) {
		scope.launch { update(authenticator.signUp(email, password)) }
	}

	fun signIn(email: String, password: String) {
		scope.launch { update(authenticator.signIn(email, password)) }
	}

	fun signIn(launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) {
		scope.launch {
			launcher.launch(
				IntentSenderRequest
					.Builder(authenticator.getSignInIntentSender() ?: return@launch)
					.build()
			)
		}
	}

	fun signIn(intent: Intent?) {
		scope.launch { update(authenticator.signInWithIntent(intent ?: return@launch)) }
	}

	fun resetPassword(email: String) {
		scope.launch { authenticator.resetPassword(email) }
	}

	fun verifyEmail() {
		scope.launch { authenticator.verifyEmail() }
	}

	fun signOut() {
		scope.launch {
			authenticator.signOut()
			update(AuthState())
		}
	}
}