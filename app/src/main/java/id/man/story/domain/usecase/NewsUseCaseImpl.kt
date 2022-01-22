package id.man.story.domain.usecase

import id.man.story.domain.model.CommentData
import id.man.story.domain.model.ResourceApi
import id.man.story.domain.model.StoryData
import id.man.story.domain.repository.Repository

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
class NewsUseCaseImpl(
    private val repository: Repository
) : NewsUseCase {

    override suspend fun getListIdTopStory(): ResourceApi<List<Int>> {
        return repository.getListIdTopStory()
    }

    override suspend fun getDetailStory(id: Int): ResourceApi<StoryData> {
        return repository.getDetailStory(id)
    }

    override suspend fun getDetailComment(id: Int): ResourceApi<CommentData> {
        return repository.getDetailComment(id)
    }


}