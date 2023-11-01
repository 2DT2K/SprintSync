package com.sprintsync.data_classes

import com.fasterxml.jackson.annotation.JsonProperty

data class Attachment(
	@JsonProperty("_id")
	val id: String,
	val name: String,
	val content: ByteArray,
	val fileType: String,
	val fileSize: Long,
	val project: String
) {
	// Need to override equals and hashCode method to compare ByteArray
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Attachment

		if (id != other.id) return false
		if (name != other.name) return false
		if (!content.contentEquals(other.content)) return false
		if (fileType != other.fileType) return false
		if (fileSize != other.fileSize) return false
		if (project != other.project) return false

		return true
	}

	override fun hashCode(): Int {
		var result = id.hashCode()
		result = 31 * result + name.hashCode()
		result = 31 * result + content.contentHashCode()
		result = 31 * result + fileType.hashCode()
		result = 31 * result + fileSize.hashCode()
		result = 31 * result + project.hashCode()
		return result
	}
}