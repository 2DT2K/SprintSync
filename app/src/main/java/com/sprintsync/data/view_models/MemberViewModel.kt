package com.sprintsync.data.view_models

import com.sprintsync.data.api.MemberAPI
import com.sprintsync.data.dtos.MemberDto
import com.sprintsync.data.dtos.RoleDto
import com.sprintsync.data.view_models.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
	private val service: MemberAPI
) : AbstractViewModel<MemberDto>() {

	private val _state = MutableStateFlow(State<RoleDto>())
	val roleState = _state.asStateFlow()
	private fun updateRole(newState: State<RoleDto>) = _state.update { newState }

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

	fun getMemberRole(projectId: String, memberId: String) {
		scope.launch {
			val response = service.getMemberRole(projectId, memberId)
			updateRole(State(message = response.data, error = response.err))
		}
	}

	fun getMe() {
		scope.launch {
			val response = service.getMe()
			update(State(dto = response.data, error = response.err))
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