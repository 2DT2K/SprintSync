package com.sprintsync.data.view_models

import com.sprintsync.data.api.AttachmentAPI
import com.sprintsync.data.dtos.AttachmentDto
import com.sprintsync.data.view_models.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttachmentViewModel @Inject constructor(
	private val service: AttachmentAPI
) : AbstractViewModel<AttachmentDto>() {
	fun getAttachment(id: String) {
		scope.launch {
			val response = service.getAttachment(id)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun getAttachmentsOfProject(id: String) {
		scope.launch {
			val response = service.getAttachmentsOfProject(id)
			update(State(dtoList = response.data, error = response.err))
		}
	}

	fun getAttachmentsOfTask(id: String) {
		scope.launch {
			val response = service.getAttachmentsOfTask(id)
			update(State(dtoList = response.data, error = response.err))
		}
	}

	fun getAttachmentsOfComment(id: String) {
		scope.launch {
			val response = service.getAttachmentsOfComment(id)
			update(State(dtoList = response.data, error = response.err))
		}
	}

	fun addAttachment(dto: AttachmentDto) {
		scope.launch {
			val response = service.addAttachment(dto)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun updateAttachment(dto: AttachmentDto) {
		scope.launch {
			val response = service.updateAttachment(dto)
			update(State(dto = response.data, error = response.err))
		}
	}

	fun deleteAttachment(id: String) {
		scope.launch {
			val response = service.deleteAttachment(id)
			update(State(message = response.data, error = response.err))
		}
	}
}