package com.sprintsync.data_classes

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class Member(
	@JsonProperty("_id")
	val id: String,
	val name: String,
	val email: String,
	val password: String,
	val dob: LocalDate
)