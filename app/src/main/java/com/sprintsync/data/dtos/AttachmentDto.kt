package com.sprintsync.data.dtos

data class AttachmentDto(
	val id: String? = null,
	val name: String,
	val fileType: String,
	val fileSize: Long,
	val createdAt: String,
	val content: ByteArray,
	val project: String,
)