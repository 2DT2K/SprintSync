package com.sprintsync.data_classes

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId

data class Project(
//	@JsonProperty("_id")
	val id: ObjectId? = null,
	val name: String,
	val manager: String,
	val statuses: List<String>,
	val labels: List<String>? = null,
	val teams: List<String>? = null,
	val sprints: List<String>? = null,
	val members: List<String>? = null,
)