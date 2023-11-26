package com.sprintsync.data.view_models

import com.sprintsync.data.api.SprintAPI
import com.sprintsync.data.dtos.SprintDto
import com.sprintsync.data.view_models.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SprintViewModel @Inject constructor(
	private val service: SprintAPI
) : AbstractViewModel<SprintDto>() {
	fun getSprint(id: String) {
		scope.launch {
			val response = service.getSprint(id)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun getSprintsOfProject(id: String) {
		scope.launch {
			val response = service.getSprintsOfProject(id)
			update(State(dtoList = response.data, error = response.err))
		}
	}

	fun addSprint(dto: SprintDto) {
		scope.launch {
			val response = service.addSprint(dto)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun updateSprint(dto: SprintDto) {
		scope.launch {
			val response = service.updateSprint(dto)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun deleteSprint(id: String) {
		scope.launch {
			val response = service.deleteSprint(id)
			update(State(message = response.data, error = response.err))
		}
	}
}