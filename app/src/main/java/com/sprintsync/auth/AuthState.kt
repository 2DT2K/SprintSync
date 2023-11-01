package com.sprintsync.auth

data class AuthState(
	val signedIn: Boolean = false,
	val errorMessage: String? = null
)