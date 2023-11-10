package com.sprintsync.data_classes

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import java.time.LocalDateTime

data class Task(
//	@JsonProperty("_id")
	val id: ObjectId? = null,
	val name: String,
	val description: String = "",
	val sprint: String,
	val team: String,
	val assignor: String,
	val assignees: List<String>,
	val parentTask: String? = null,
	val attachments: List<String>? = null,
	val statusIndex: Int,
	val deadline: String? = null,
	val point: Int = 0,
	val comments: List<String>? = null,
	val label: String? = null
)