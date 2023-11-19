package com.sprintsync.api

import com.sprintsync.dtos.MemberDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
	@POST("auth/sign_up")
	suspend fun signUp(@Body dto: MemberDto): Response<MemberDto>
}