package id.man.story.data.network.dto

import com.google.gson.annotations.SerializedName
import id.man.story.domain.extention.toDate
import id.man.story.domain.model.StoryData

data class DetailStoryResponse(

    @SerializedName("score")
    val score: Int? = null,

    @SerializedName("by")
    val by: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("time")
    val time: Long? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("descendants")
    val descendants: Int? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("kids")
    val kids: List<Int>? = null

) {

    companion object {
        fun DetailStoryResponse.toDomain(): StoryData {
            return StoryData(
                id = id ?: 0,
                by = by ?: "",
                score = score ?: 0,
                time = time?.toDate() ?: "",
                title = title ?: "",
                type = type ?: "",
                descendants = descendants ?: 0,
                url = url ?: "",
                commentsId = kids ?: listOf()
            )
        }
    }

}
