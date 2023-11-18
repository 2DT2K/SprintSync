package com.sprintsync.data_classes

import org.bson.types.ObjectId

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