package com.sprintsync.data_classes

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import java.time.LocalDate

data class Member(
	val id: ObjectId? = null,
	val name: String,
	val email: String,
	val password: String,
	val dob: String
)