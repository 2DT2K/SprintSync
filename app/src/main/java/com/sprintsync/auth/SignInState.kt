package com.sprintsync.auth

data class SignInState(
	val isSignInSuccessful: Boolean = false,
	val errorMessage: String? = null
)
