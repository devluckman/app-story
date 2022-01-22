package id.man.story.domain.model

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
data class CommentData(
    val parent: Int,
    val by: String,
    val id: Int,
    val text: String,
    val time: String,
    val type: String,
    val kids: List<Int>
)