package com.sprintsync.data.view_models

import android.util.Log
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
        setLoading(true)
        scope.launch {
            try {
                val response = service.getTeam(id)
                update(State(dto = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("TeamViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun getTeamsOfProject(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getTeamsOfProject(id)
                update(State(dtoList = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("TeamViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun addTeam(dto: TeamDto) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.addTeam(dto)
                update(
                    State(
                        dto = response.data,
                        dtoList = state.value.dtoList,
                        error = response.err
                    )
                )
                response.data?.let { addToDtoList(it) }
            } catch (e: Exception) {
                Log.e("TeamViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun addMember(email: String, teamId: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.addMember(email, teamId)
                update(
                    State(
                        dto = response.data,
                        dtoList = state.value.dtoList,
                        error = response.err
                    )
                )
                response.data?.let { addToDtoList(it) }
            } catch (e: Exception) {
                Log.e("TeamViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun updateTeam(dto: TeamDto) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.updateTeam(dto)
                update(State(dto = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("TeamViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun deleteTeam(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.deleteTeam(id)
                update(State(message = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("TeamViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }
}