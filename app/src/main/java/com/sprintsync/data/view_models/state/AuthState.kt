package com.sprintsync.data.view_models.state

data class AuthState(
	val signedIn: Boolean = false,
	val errorCode: String? = null,
	val errorMessage: String? = null,
)
