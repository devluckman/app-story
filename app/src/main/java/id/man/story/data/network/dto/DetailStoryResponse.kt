package id.man.story.data.network.dto

import com.google.gson.annotations.SerializedName
import id.man.story.domain.extention.toDate
import id.man.story.domain.model.StoryData

data class DetailStoryResponse(

    @SerializedName("score")
    val score: Int,

    @SerializedName("by")
    val by: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("time")
    val time: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("descendants")
    val descendants: Int,

    @SerializedName("url")
    val url: String,

    @SerializedName("kids")
    val kids: List<Int>

) {

    companion object {
        fun DetailStoryResponse.toDomain(): StoryData {
            return StoryData(
                id = id,
                by = by,
                score = score,
                time = time.toDate(),
                title = title,
                type = type,
                descendants = descendants,
                url = url,
                commentsId = kids
            )
        }
    }

}
