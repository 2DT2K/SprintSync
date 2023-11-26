package com.sprintsync.data.view_models.state

data class State<T>(
	val dto: T? = null,
	val dtoList: List<T>? = null,
	val message: String? = null,
	val error: String? = null
)