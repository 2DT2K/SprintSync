package com.sprintsync.api

import com.sprintsync.data_classes.Member
import retrofit2.http.GET

interface MemberAPI {
	@GET("members")
	suspend fun getMembers(): List<Member>
}