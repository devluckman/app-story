package id.man.story.data.network.api

import id.man.story.data.network.dto.DetailCommentResponse
import id.man.story.data.network.dto.DetailStoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
interface Api {

    @GET("topstories.json")
    suspend fun getListIdTopStory(): Response<List<Int>>

    @GET("item/{id}.json")
    suspend fun getDetailStory(
        @Path("id") id: Int
    ): Response<DetailStoryResponse>

    @GET("item/{id}.json")
    suspend fun getDetailComment(
        @Path("id") id: Int
    ): Response<DetailCommentResponse>

}