package com.sprintsync.data.view_models

import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sprintsync.data.api.AuthAPI
import com.sprintsync.data.auth.Authenticator
import com.sprintsync.data.dtos.MemberDto
import com.sprintsync.data.view_models.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authenticator: Authenticator, private val service: AuthAPI
) : ViewModel() {
    private val scope = viewModelScope

    private val _state = MutableStateFlow(AuthState(Authenticator.signedIn))
    val state = _state.asStateFlow()

    fun signUp(email: String, password: String) {
        scope.launch {
            try {
                update(authenticator.signUp(email, password))
                Authenticator.signedInUser?.let { service.signUp(MemberDto(it)) }
            } catch (e: Exception) {
                Log.e("AuthViewModel", e.message ?: "Error")
            }
        }

    }

    fun signIn(email: String, password: String) {
        scope.launch { update(authenticator.signIn(email, password)) }
    }

    fun signIn(launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) {
        scope.launch {
            try {
                launcher.launch(
                    IntentSenderRequest.Builder(
                            authenticator.getSignInIntentSender() ?: return@launch
                        ).build()
                )
            } catch (e: Exception) {
                Log.e("AuthViewModel", e.message ?: "Error")
            }

        }
    }

    fun signIn(intent: Intent?) {
        scope.launch {
            try {
                update(authenticator.signInWithIntent(intent ?: return@launch))
//			TODO: perfect this later (add if needed, update mongo when override)
                Authenticator.signedInUser?.let { service.signUp(MemberDto(it)) }
            } catch (e: Exception) {
                Log.e("AuthViewModel", e.message ?: "Error")
            }
        }
    }

    fun resetPassword(email: String, callback: () -> Unit) {
        try {
            scope.launch { authenticator.resetPassword(email); callback() }
        } catch (e: Exception) {
            Log.e("AuthViewModel", e.message ?: "Error")
        }

    }

    fun verifyEmail() {
        try {
            scope.launch { authenticator.verifyEmail() }
        } catch (e: Exception) {
            Log.e("AuthViewModel", e.message ?: "Error")
        }
    }

    fun signOut() {
        try {
            scope.launch {
                authenticator.signOut()
                update(AuthState())
            }
        } catch (e: Exception) {
            Log.e("AuthViewModel", e.message ?: "Error")
        }
    }

    private fun update(newState: AuthState) = _state.update { newState }
}