package com.sprintsync.data_classes

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import java.time.LocalDateTime

data class Sprint(
//	@JsonProperty("_id")
	val id: ObjectId? = null,
	val sprintName: String,
	val startDate: String,
	val endDate: String,
	val sprintNumber: Int,
	val task: List<ObjectId>? = null,
	val project: ObjectId,
	val isDone: Boolean
)