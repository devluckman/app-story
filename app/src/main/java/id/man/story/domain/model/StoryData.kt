package id.man.story.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
@Parcelize
data class StoryData(
    val score: Int,
    val by: String,
    val id: Int,
    val time: String,
    val title: String,
    val type: String,
    val descendants: Int,
    val url: String,
    val commentsId: List<Int>
) : Parcelable {

    companion object {

        fun StoryData.toFirebase(): StoryDataFirebase =
            StoryDataFirebase(
                score, by, id, time, title, type, descendants, url, commentsId
            )

    }

}