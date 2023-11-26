package com.sprintsync.data.view_models

import com.sprintsync.data.api.TeamAPI
import com.sprintsync.data.dtos.TeamDto
import com.sprintsync.data.dtos.response.TeamResDto
import com.sprintsync.data.view_models.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
	private val service: TeamAPI
) : AbstractViewModel<TeamResDto>() {
	fun getTeam(id: String) {
		scope.launch {
			val response = service.getTeam(id)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun getTeamsOfProject(id: String) {
		scope.launch {
			val response = service.getTeamsOfProject(id)
			update(State(dtoList = response.data, error = response.err))
		}
	}

	fun addTeam(dto: TeamDto) {
		scope.launch {
			val response = service.addTeam(dto)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun updateTeam(dto: TeamDto) {
		scope.launch {
			val response = service.updateTeam(dto)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun deleteTeam(id: String) {
		scope.launch {
			val response = service.deleteTeam(id)
			update(State(message = response.data, error = response.err))
		}
	}
}