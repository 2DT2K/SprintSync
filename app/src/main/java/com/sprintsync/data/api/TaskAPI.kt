package com.sprintsync.data.api

import com.sprintsync.data.dtos.TaskDto
import com.sprintsync.data.dtos.response.ApiResponse
import com.sprintsync.data.dtos.response.TaskResDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskAPI {
	@GET("tasks/{id}")
	suspend fun getTask(@Path("id") id: String): ApiResponse<TaskResDto>

	@GET("tasks/project/{id}")
	suspend fun getTasksOfProject(@Path("id") id: String): ApiResponse<List<TaskResDto>>

	@GET("tasks/sprint/{id}")
	suspend fun getTasksOfSprint(@Path("id") id: String): ApiResponse<List<TaskResDto>>

	@GET("tasks/team/{id}")
	suspend fun getTasksOfTeam(@Path("id") id: String): ApiResponse<List<TaskResDto>>

	@GET("tasks/me")
	suspend fun getMyTasks(): ApiResponse<List<TaskResDto>>

	@POST("tasks")
	suspend fun addTask(@Body dto: TaskDto): ApiResponse<TaskResDto>

	@PATCH("tasks")
	suspend fun updateTask(@Body dto: TaskDto): ApiResponse<TaskResDto>

	@DELETE("tasks/{id}")
	suspend fun deleteTask(@Path("id") id: String): ApiResponse<String>
}