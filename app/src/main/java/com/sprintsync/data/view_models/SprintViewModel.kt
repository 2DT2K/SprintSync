package com.sprintsync.data.view_models

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import com.sprintsync.data.api.SprintAPI
import com.sprintsync.data.dtos.SprintDto
import com.sprintsync.data.dtos.response.ReportChartDto
import com.sprintsync.data.view_models.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SprintViewModel @Inject constructor(
    private val service: SprintAPI
) : AbstractViewModel<SprintDto>() {
    private val _report = MutableStateFlow(ReportChartDto())
    val report = _report.asStateFlow()

    private val _activeSprint = MutableStateFlow<SprintDto?>(null)
    val activeSprint = _activeSprint.asStateFlow()

    private fun updateReport(dto: ReportChartDto) = _report.update { dto }

    private fun updateActiveSprint(newState: SprintDto) = _activeSprint.update { newState }

    fun getSprint(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getSprint(id)
                update(State(dto = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("SprintViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun getSprintsOfProject(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getSprintsOfProject(id)
                update(State(dto = state.value.dto, dtoList = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("SprintViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun getSprintReport(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getSprintReport(id)
                response.data?.let { updateReport(it) }
            } catch (e: Exception) {
                Log.e("SprintViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun getActiveSprintByProject(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getActiveSprintByProject(id)
                response.data?.let { updateActiveSprint(it) }
            } catch (e: Exception) {
                Log.e("SprintViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun getInitialSprintReport(projectId: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getSprintsOfProject(projectId)
                update(State(dtoList = response.data, error = response.err))
                val sprints = response.data ?: emptyList()
                val initialSprintsId = sprints.lastOrNull()?.id
                val sprintReport = initialSprintsId?.let { service.getSprintReport(it) }
                if (sprintReport != null) {
                    sprintReport.data?.let { updateReport(it) }
                }
            } catch (e: Exception) {
                Log.e("SprintViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun addSprint(dto: SprintDto) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.addSprint(dto)
                update(
                    State(
                        dto = response.data,
                        dtoList = state.value.dtoList,
                        error = response.err
                    )
                )
                response.data?.let { addToDtoList(it) }
            } catch (e: Exception) {
                Log.e("SprintViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun updateSprint(dto: SprintDto) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.updateSprint(dto)
                if (response.data != null) {
                    update(
                        State(
                            dto = response.data,
                            dtoList = state.value.dtoList,
                            error = response.err
                        )
                    )
                }
            } catch (e: Exception) {
                Log.e("SprintViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun deleteSprint(id: String) {
        setLoading(true)
        scope.launch {
            try{
                val response = service.deleteSprint(id)
                update(State(message = response.data, error = response.err))
            }
            catch (e: Exception){
                Log.e("SprintViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

}