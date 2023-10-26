package com.sprintsync.data_classes

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class Sprint(
	@JsonProperty("_id")
	val id: String,
	val startDate: LocalDateTime,
	val endDate: LocalDateTime,
	val sprintNumber: Int,
	val project: String
)