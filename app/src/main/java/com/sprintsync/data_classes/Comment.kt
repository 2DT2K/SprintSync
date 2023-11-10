package com.sprintsync.data_classes

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId

data class Comment(
//	@JsonProperty("_id")
	val id: ObjectId? = null,
	val commenter: ObjectId,
	val content: String,
	val attachments: List<ObjectId>? = null,
)