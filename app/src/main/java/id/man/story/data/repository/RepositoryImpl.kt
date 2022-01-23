package id.man.story.data.repository

import id.man.story.data.network.api.Api
import id.man.story.data.network.dto.DetailCommentResponse.Companion.toDomain
import id.man.story.data.network.dto.DetailStoryResponse.Companion.toDomain
import id.man.story.domain.model.CommentData
import id.man.story.domain.model.ResourceApi
import id.man.story.domain.model.StoryData
import id.man.story.domain.repository.Repository

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
class RepositoryImpl(
    private val api: Api
) : Repository {

    override suspend fun getListIdTopStory(): ResourceApi<List<Int>> {
        return try {
            val data = api.getListIdTopStory()

            if (data.body() != null) {
                ResourceApi.OnSuccess(data.body()!!)
            } else {
                ResourceApi.OnError("Empty Response")
            }

        } catch (e: Exception) {
            ResourceApi.OnError(e.message ?: "Something Error")
        }
    }

    override suspend fun getDetailStory(
        id: Int
    ): ResourceApi<StoryData> {

        return try {
            val data = api.getDetailStory(id)

            if (data.body() != null) {
                ResourceApi.OnSuccess(data.body()!!.toDomain())
            } else {
                ResourceApi.OnError("Empty Response")
            }

        } catch (e: Exception) {
            ResourceApi.OnError(e.message ?: "Something Error")
        }

    }

    override suspend fun getDetailComment(id: Int): ResourceApi<CommentData> {
        return try {
            val data = api.getDetailComment(id)

            if (data.body() != null) {
                ResourceApi.OnSuccess(data.body()!!.toDomain())
            } else {
                ResourceApi.OnError("Empty Response")
            }

        } catch (e: Exception) {
            e.printStackTrace()
            ResourceApi.OnError(e.message ?: "Something Error")
        }
    }
}