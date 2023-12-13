package com.sprintsync.data.view_models

import android.util.Log
import com.google.android.play.integrity.internal.f
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
        setLoading(true)
        scope.launch {
            try {
                val response = service.getMember(id)
                update(State(dto = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("MemberViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun getMembersOfProject(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getMembersOfProject(id)
                update(State(dtoList = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("MemberViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun getMembersOfTeam(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getMembersOfTeam(id)
                update(State(dtoList = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("MemberViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun getMemberRole(memberId: String, projectId: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getMemberRole(memberId, projectId)
                updateRole(State(message = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("MemberViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun getMe() {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getMe()
                update(State(dto = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("MemberViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun addDevice(token: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.addDevice(token)
                update(State(message = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("MemberViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun updateMember(dto: MemberDto) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.updateMember(dto)
                update(State(dto = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("MemberViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun deleteMe() {
        setLoading(true)
        scope.launch {
            try {
                val response = service.deleteMe()
                update(State(message = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("MemberViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }
}