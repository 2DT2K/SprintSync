package com.sprintsync.auth

import android.net.Uri

data class UserData(
	val uid: String,
	val name: String?,
	val email: String?,
	val verified: Boolean,
	val profilePictureUrl: Uri?
)
