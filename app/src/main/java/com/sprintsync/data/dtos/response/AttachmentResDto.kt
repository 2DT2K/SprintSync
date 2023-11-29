package com.sprintsync.data.dtos.response

data class AttachmentResDto(
    val id: String? = null,
    val name: String,
    val fileType: String,
    val fileSize: Long,
    val createdAt: String,
    val content: String,
    val project: String,
)