package com.sprintsync.data.view_models

import com.sprintsync.data.api.TaskAPI
import com.sprintsync.data.dtos.TaskDto
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.data.view_models.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
	private val service: TaskAPI
) : AbstractViewModel<TaskResDto>() {
	fun getTask(id: String) {
		scope.launch {
			val response = service.getTask(id)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun getTasksOfProject(id: String) {
		scope.launch {
			val response = service.getTasksOfProject(id)
			update(State(dtoList = response.data, error = response.err))
		}
	}

	fun getTasksOfSprint(id: String) {
		scope.launch {
			val response = service.getTasksOfSprint(id)
			update(State(dtoList = response.data, error = response.err))
		}
	}

	fun getTasksOfTeam(id: String) {
		scope.launch {
			val response = service.getTasksOfTeam(id)
			update(State(dtoList = response.data, error = response.err))
		}
	}

	fun getMyTasks() {
		scope.launch {
			val response = service.getMyTasks()
			update(State(dtoList = response.data, error = response.err))
		}
	}

	fun addTask(task: TaskDto) {
		scope.launch {
			val response = service.addTask(task)
			update(State(dto = response.data, dtoList = state.value.dtoList, error = response.err))
			response.data?.let { addToDtoList(it) }
		}
	}

	fun updateTask(task: TaskDto) {
		scope.launch {
			val response = service.updateTask(task)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun deleteTask(id: String) {
		scope.launch {
			val response = service.deleteTask(id)
			update(State(message = response.data, error = response.err))
		}
	}
}