package id.man.story.data.network.dto

import com.google.gson.annotations.SerializedName
import id.man.story.domain.extention.toDate
import id.man.story.domain.model.CommentData
import id.man.story.domain.model.StoryData

data class DetailCommentResponse(

	@SerializedName("parent")
	val parent: Int? = null,

	@SerializedName("by")
	val by: String? = null,

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("text")
	val text: String? = null,

	@SerializedName("time")
	val time: Long? = null,

	@SerializedName("type")
	val type: String? = null,

	@SerializedName("kids")
	val kids: List<Int>? = null
){
	companion object {
		fun DetailCommentResponse.toDomain(): CommentData {
			return CommentData(
				id = id ?: 0,
				by = by ?: "",
				time = time?.toDate() ?: "",
				type = type ?: "",
				kids = kids ?: listOf(),
				parent = parent ?: 0,
				text = text ?: ""
			)
		}
	}
}
