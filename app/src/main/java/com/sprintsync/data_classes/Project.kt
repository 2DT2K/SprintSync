package com.sprintsync.data_classes

import com.fasterxml.jackson.annotation.JsonProperty

data class Project(
	@JsonProperty("_id")
	val id: String,
	val name: String,
	val manager: String,
	val statuses: List<String>,
	val labels: List<String>? = null,
)