package com.sprintsync.data.view_models

import android.util.Log
import com.sprintsync.data.api.TaskAPI
import com.sprintsync.data.dtos.TaskDto
import com.sprintsync.data.dtos.response.TaskResDto
import com.sprintsync.data.view_models.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val service: TaskAPI
) : AbstractViewModel<TaskResDto>() {
    private val _subtasks = MutableStateFlow<List<TaskResDto>>(emptyList())
    val subtasks = _subtasks.asStateFlow()
    private fun updateSubtasks(newState: List<TaskResDto>) = _subtasks.update { newState }
    fun getTask(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getTask(id)
                update(State(dto = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("TaskViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun getSubTasks(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getSubTasks(id)
                response.data?.let { updateSubtasks(it) }
            } catch (e: Exception) {
                Log.e("TaskViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun getTasksOfProject(id: String) {
        setLoading(true)
        scope.launch {
            val response = service.getTasksOfProject(id)
            update(State(dtoList = response.data, error = response.err))
            setLoading(false)
        }
    }

    fun getTasksOfSprint(id: String) {
        setLoading(true)
        scope.launch {
            val response = service.getTasksOfSprint(id)
            update(State(dtoList = response.data, error = response.err))
            setLoading(false)
        }
    }

    fun getTasksOfTeam(id: String) {
        setLoading(true)
        scope.launch {
            val response = service.getTasksOfTeam(id)
            update(State(dtoList = response.data, error = response.err))
            setLoading(false)
        }
    }

    fun getMyTasks() {
        setLoading(true)
        scope.launch {
            val response = service.getMyTasks()
            update(State(dtoList = response.data, error = response.err))
            setLoading(false)
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