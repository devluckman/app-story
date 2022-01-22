package id.man.story.domain.model

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
sealed class ResourceApi<out T> {
    data class OnSuccess<T>(val data: T) : ResourceApi<T>()
    data class OnError(val message: String) : ResourceApi<Nothing>()
}
