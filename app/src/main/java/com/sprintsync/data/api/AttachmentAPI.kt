package com.sprintsync.data.api

import com.sprintsync.data.dtos.AttachmentDto
import com.sprintsync.data.dtos.response.ApiResponse
import com.sprintsync.data.dtos.response.AttachmentResDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface AttachmentAPI {
	@GET("attachments/{id}")
	suspend fun getAttachment(@Path("id") id: String): ApiResponse<AttachmentResDto>

	@GET("attachments/project/{id}")
	suspend fun getAttachmentsOfProject(@Path("id") id: String): ApiResponse<List<AttachmentResDto>>

	@GET("attachments/task/{id}")
	suspend fun getAttachmentsOfTask(@Path("id") id: String): ApiResponse<List<AttachmentResDto>>

	@GET("attachments/comment/{id}")
	suspend fun getAttachmentsOfComment(@Path("id") id: String): ApiResponse<List<AttachmentResDto>>

	@POST("attachments")
	suspend fun addAttachment(@Body dto: AttachmentDto): ApiResponse<AttachmentResDto>

	@PATCH("attachments")
	suspend fun updateAttachment(@Body dto: AttachmentDto): ApiResponse<AttachmentResDto>

	@DELETE("attachments/{id}")
	suspend fun deleteAttachment(@Path("id") id: String): ApiResponse<String>
}