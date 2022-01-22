package id.man.story.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.man.story.domain.model.CommentData
import id.man.story.domain.model.ResourceApi
import id.man.story.domain.usecase.NewsUseCase
import id.man.story.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
class DetailStoryVM(
    private val useCase: NewsUseCase
) : BaseViewModel() {

    // region Attribute

    private val _comments = MutableLiveData<List<CommentData>>()
    val comments = _comments as LiveData<List<CommentData>>

    // endregion

    // region Get Comment

    fun getComment(id: Int) {
        viewModelScope.launch {
            val data = useCase.getDetailComment(id)
            if (data is ResourceApi.OnSuccess) {
                _comments.value = listOf(data.data)
            }
        }
    }

    // endregion

}