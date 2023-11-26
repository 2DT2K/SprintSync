package com.sprintsync.data.api

import com.sprintsync.data.dtos.MemberDto
import com.sprintsync.data.dtos.response.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
	@POST("auth/sign_up")
	suspend fun signUp(@Body dto: MemberDto): ApiResponse<String>
}