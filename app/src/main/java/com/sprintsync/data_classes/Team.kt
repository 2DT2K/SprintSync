package com.sprintsync.data_classes

import org.bson.types.ObjectId

data class Team(
//	@JsonProperty("_id")
	val id: ObjectId? = null,
	val name: String,
	val teamLeader: ObjectId,
	val members: List<ObjectId>,
	val project: ObjectId
)