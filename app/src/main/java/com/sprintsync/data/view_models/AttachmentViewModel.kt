package com.sprintsync.data.view_models

import android.util.Log
import com.sprintsync.data.api.AttachmentAPI
import com.sprintsync.data.dtos.AttachmentDto
import com.sprintsync.data.dtos.response.AttachmentResDto
import com.sprintsync.data.view_models.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttachmentViewModel @Inject constructor(
    private val service: AttachmentAPI
) : AbstractViewModel<AttachmentResDto>() {
    fun getAttachment(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getAttachment(id)
                update(State(dto = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("AttachmentViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun getAttachmentsOfProject(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getAttachmentsOfProject(id)
                update(State(dtoList = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("AttachmentViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun getAttachmentsOfTask(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getAttachmentsOfTask(id)
                update(State(dtoList = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("AttachmentViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }

        }
    }

    fun getAttachmentsOfComment(id: String) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.getAttachmentsOfComment(id)
                update(State(dtoList = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("AttachmentViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun addAttachment(dto: AttachmentDto) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.addAttachment(dto)
                update(
                    State(
                        dto = response.data,
                        dtoList = state.value.dtoList,
                        error = response.err
                    )
                )
                response.data?.let { addToDtoList(it) }
            } catch (e: Exception) {
                Log.e("AttachmentViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun updateAttachment(dto: AttachmentDto) {
        setLoading(true)
        scope.launch {
            try {
                val response = service.updateAttachment(dto)
                update(State(dto = response.data, error = response.err))
            } catch (e: Exception) {
                Log.e("AttachmentViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun deleteAttachment(id: String) {
        setLoading(true)
        scope.launch {
            try{
                val response = service.deleteAttachment(id)
                update(State(message = response.data, error = response.err))
            }
            catch (e: Exception){
                Log.e("AttachmentViewModel", e.message ?: "Error")
            } finally {
                setLoading(false)
            }
        }
    }
}