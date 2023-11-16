package com.sprintsync.auth

data class UserData(
	val uid: String,
	val name: String?,
	val email: String?,
	val isVerified: Boolean
)
