package com.sprintsync.dtos
data class MemberDto(
	val id: String? = null,
	val uid: String,
	val name: String,
	val email: String,
	val dob: String? = null
)