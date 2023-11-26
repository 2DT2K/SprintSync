package com.sprintsync.data.api

import com.sprintsync.data.dtos.AttachmentDto
import com.sprintsync.data.dtos.response.ApiResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface AttachmentAPI {
	@GET("attachments/{id}")
	suspend fun getAttachment(@Path("id") id: String): ApiResponse<AttachmentDto>

	@GET("attachments/project/{id}")
	suspend fun getAttachmentsOfProject(@Path("id") id: String): ApiResponse<List<AttachmentDto>>

	@GET("attachments/task/{id}")
	suspend fun getAttachmentsOfTask(@Path("id") id: String): ApiResponse<List<AttachmentDto>>

	@GET("attachments/comment/{id}")
	suspend fun getAttachmentsOfComment(@Path("id") id: String): ApiResponse<List<AttachmentDto>>

	@POST("attachments")
	suspend fun addAttachment(@Body dto: AttachmentDto): ApiResponse<AttachmentDto>

	@PATCH("attachments")
	suspend fun updateAttachment(@Body dto: AttachmentDto): ApiResponse<AttachmentDto>

	@DELETE("attachments/{id}")
	suspend fun deleteAttachment(@Path("id") id: String): ApiResponse<String>
}