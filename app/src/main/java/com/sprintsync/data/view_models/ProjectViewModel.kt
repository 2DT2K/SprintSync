package com.sprintsync.data.view_models

import android.util.Log
import com.sprintsync.data.api.ProjectAPI
import com.sprintsync.data.dtos.ProjectDto
import com.sprintsync.data.view_models.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
	private val service: ProjectAPI
) : AbstractViewModel<ProjectDto>() {
	fun getProject(id: String) {
		scope.launch {
			val response = service.getProject(id)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun getMyProjects() {
		scope.launch {
			val response = service.getMyProjects()
			update(State(dtoList = response.data, error = response.err))
		}
	}

	fun choseProject(project: ProjectDto) {
		scope.launch {
			Log.d("log-bug-api","chosen project$project")
			update(State(dto = project))
		}
	}

	fun addProject(dto: ProjectDto) {
		scope.launch {
			val response = service.addProject(dto)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun updateProject(dto: ProjectDto) {
		scope.launch {
			val response = service.updateProject(dto)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun deleteProject(id: String) {
		scope.launch {
			val response = service.deleteProject(id)
			update(State(message = response.data, error = response.err))
		}
	}
}