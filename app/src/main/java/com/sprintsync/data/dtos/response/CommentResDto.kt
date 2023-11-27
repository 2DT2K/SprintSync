package com.sprintsync.data.dtos.response

import com.sprintsync.data.dtos.AttachmentDto
import com.sprintsync.data.dtos.MemberDto

data class CommentResDto (
    val id: String? = null,
    val commenter: MemberDto,
    val content: String,
    val createdAt: String,
    val attachments: List<AttachmentDto>? = null,
)