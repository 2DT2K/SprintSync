package com.sprintsync.data_classes

import org.bson.types.ObjectId

data class Task(
//	@JsonProperty("_id")
	val id: ObjectId? = null,
	val name: String,
	val description: String = "",
	val sprint: ObjectId,
	val team: ObjectId,
	val assignor: ObjectId,
	val assignees: List<ObjectId>,
	val parentTask: ObjectId? = null,
	val attachments: List<ObjectId>? = null,
	val statusIndex: Int,
	val deadline: String? = null,
	val point: Int = 0,
	val comments: List<ObjectId>? = null,
	val label: String? = null
)