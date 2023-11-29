package com.sprintsync.data.view_models

import com.sprintsync.data.api.MemberAPI
import com.sprintsync.data.dtos.MemberDto
import com.sprintsync.data.view_models.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
	private val service: MemberAPI
) : AbstractViewModel<MemberDto>() {
	fun getMember(id: String) {
		scope.launch {
			val response = service.getMember(id)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun getMembersOfProject(id: String) {
		scope.launch {
			val response = service.getMembersOfProject(id)
			update(State(dtoList = response.data, error = response.err))
		}
	}

	fun getMembersOfTeam(id: String) {
		scope.launch {
			val response = service.getMembersOfTeam(id)
			update(State(dtoList = response.data, error = response.err))
		}
	}

	fun getMe() {
		scope.launch {
			val response = service.getMe()
			update(State(dto = response.data, error = response.err))
		}
	}

	fun addDevice(token: String) {
		scope.launch {
			val response = service.addDevice(token)
			update(State(message = response.data, error = response.err))
		}
	}

	fun updateMember(dto: MemberDto) {
		scope.launch {
			val response = service.updateMember(dto)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun deleteMe() {
		scope.launch {
			val response = service.deleteMe()
			update(State(message = response.data, error = response.err))
		}
	}
}