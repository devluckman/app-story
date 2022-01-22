package id.man.story.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.man.story.domain.model.ResourceApi
import id.man.story.domain.model.StoryData
import id.man.story.domain.usecase.NewsUseCase
import id.man.story.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
class MainVM(
    private val useCase: NewsUseCase
) : BaseViewModel() {

    // region Attribute

    private val mutableUi = MutableLiveData<MainState>(MainState.ShowLoading)
    val uiState = mutableUi as LiveData<MainState>
    private val _dataListId = mutableListOf<Int>()
    private var index = 0
    // endregion

    // region Get List Id Top Story

    fun getListIdTopStory() {
        mutableUi.value = MainState.ShowLoading
        viewModelScope.launch {
            try {
                val response = useCase.getListIdTopStory()
                handleListId(response)
            } catch (t: Throwable) {
                errorMessage.value = t.message
            }
        }
    }

    private fun handleListId(data: ResourceApi<List<Int>>) {
        when (data) {
            is ResourceApi.OnSuccess -> {
                _dataListId.addAll(data.data)
                checkData()
            }
            is ResourceApi.OnError -> {
                errorMessage.value = data.message
            }
        }
    }

    // endregion

    // region Get List Story

    private fun checkData() {
        if (index < _dataListId.size) {
            fetchData()
        } else {
            mutableUi.value = MainState.IsFetchDataDone
        }
    }

    private fun fetchData() {
        val temp = _dataListId[index]
        viewModelScope.launch {
            val data = useCase.getDetailStory(temp)
            if (data is ResourceApi.OnSuccess) {
                mutableUi.postValue(MainState.OnGetDataStory(listOf(data.data)))
            }
        }
    }

    // endregion

    // region Sealed UI State

    sealed class MainState {
        data class OnGetDataStory(val data: List<StoryData>) : MainState()
        data class OnSetProgress(val percent: Int) : MainState()
        object ShowLoading : MainState()
        object IsFetchDataDone : MainState()
    }

    // endregion
}