package id.man.story.domain.repository

import id.man.story.domain.model.CommentData
import id.man.story.domain.model.ResourceApi
import id.man.story.domain.model.StoryData

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
interface Repository {

    suspend fun getListIdTopStory(): ResourceApi<List<Int>>

    suspend fun getDetailStory(id: Int): ResourceApi<StoryData>

    suspend fun getDetailComment(id: Int): ResourceApi<CommentData>
}