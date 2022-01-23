package id.man.story.domain.model

/**
 *
 * Created by Lukmanul Hakim on  1/23/2022
 * devs.lukman@gmail.com
 */
data class StoryDataFirebase(
    val score: Int? = 0,
    val by: String? = "",
    val id: Int? = 0,
    val time: String? = "",
    val title: String? = "",
    val type: String? = "",
    val descendants: Int? = 0,
    val url: String? = "",
    val commentsId: List<Int>? = listOf()
)