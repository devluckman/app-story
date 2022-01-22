package id.man.story.data.network.dto

import com.google.gson.annotations.SerializedName
import id.man.story.domain.extention.toDate
import id.man.story.domain.model.CommentData
import id.man.story.domain.model.StoryData

data class DetailCommentResponse(

	@SerializedName("parent")
	val parent: Int,

	@SerializedName("by")
	val by: String,

	@SerializedName("id")
	val id: Int,

	@SerializedName("text")
	val text: String,

	@SerializedName("time")
	val time: Long,

	@SerializedName("type")
	val type: String,

	@SerializedName("kids")
	val kids: List<Int>
){
	companion object {
		fun DetailCommentResponse.toDomain(): CommentData {
			return CommentData(
				id = id,
				by = by,
				time = time.toDate(),
				type = type,
				kids = kids,
				parent = parent,
				text = text
			)
		}
	}
}
