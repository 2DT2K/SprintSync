package com.sprintsync.data.view_models.state

data class AuthState(
	val signedIn: Boolean = false,
	val errorMessage: String? = null,
)
