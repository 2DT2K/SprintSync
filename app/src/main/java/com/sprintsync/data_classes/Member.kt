package com.sprintsync.data_classes

import org.bson.types.ObjectId

data class Member(
	val id: ObjectId? = null,
	val name: String,
	val email: String,
	val dob: String
)