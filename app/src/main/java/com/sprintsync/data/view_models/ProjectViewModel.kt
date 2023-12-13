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
        setLoading(true)
        scope.launch {
            try {
                val response = service.getProject(id)
                update(State(dto = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("ProjectViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }

    }

    fun getMyProjects() {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getMyProjects()
                update(State(dtoList = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("ProjectViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun choseProject(project: ProjectDto) {
        setLoading(true)
        scope.launch {
            try {
                Log.d("log-bug-api", "chosen project$project")
                update(State(dto = project))
            } catch (e: Exception) {
                Log.e("ProjectViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun addProject(dto: ProjectDto) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.addProject(dto)
                update(
                    State(
                        dto = response.data,
                        dtoList = state.value.dtoList,
                        error = response.err
                    )
                )
                response.data?.let { addToDtoList(it) }
            } catch (e: Exception) {
                Log.e("ProjectViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }

        }
    }

    fun updateProject(dto: ProjectDto) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.updateProject(dto)
                update(State(dto = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("ProjectViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun deleteProject(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.deleteProject(id)
                update(State(message = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("ProjectViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }
}