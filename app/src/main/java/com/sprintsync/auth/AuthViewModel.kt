package com.sprintsync.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel : ViewModel() {
	private val _state = MutableStateFlow(AuthState())
	val state = _state.asStateFlow()

	fun update(newState: AuthState) = _state.update { newState }

	fun reset() = _state.update { AuthState() }
}