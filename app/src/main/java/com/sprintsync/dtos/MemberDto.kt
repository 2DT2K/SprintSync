package com.sprintsync.dtos

import com.sprintsync.auth.UserData

data class MemberDto(
	val id: String? = null,
	val uid: String,
	val name: String,
	val email: String,
	val dob: String? = null
) {
	constructor(data: UserData) : this(
		uid = data.uid,
		name = data.name,
		email = data.email
	)
}
