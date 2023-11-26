package com.sprintsync.data.dtos.response

data class ApiResponse<T>(
	val data: T? = null,
	val err: String? = null
)