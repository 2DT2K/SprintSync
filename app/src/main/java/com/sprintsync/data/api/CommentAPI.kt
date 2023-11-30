package com.sprintsync.data.api

import com.sprintsync.data.dtos.CommentDto
import com.sprintsync.data.dtos.response.ApiResponse
import com.sprintsync.data.dtos.response.CommentResDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentAPI {
    @GET("comments/{id}")
    suspend fun getComment(@Path("id") id: String): ApiResponse<CommentResDto>

    @GET("comments/task/{id}")
    suspend fun getCommentsOfTask(@Path("id") id: String): ApiResponse<List<CommentResDto>>

    @POST("comments")
    suspend fun addComment(@Body dto: CommentDto): ApiResponse<CommentResDto>

    @POST("comments/task/{id}")
    suspend fun addCommentToTask(
        @Body dto: CommentDto,
        @Path("id") id: String
    ): ApiResponse<CommentResDto>

    @PATCH("comments")
    suspend fun updateComment(@Body dto: CommentDto): ApiResponse<CommentResDto>

    @DELETE("comments/{id}")
    suspend fun deleteComment(@Path("id") id: String): ApiResponse<String>
}