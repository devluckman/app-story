package id.man.story.presentation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.man.paging.BaseListVM
import id.man.story.domain.model.ResourceApi
import id.man.story.domain.model.StoryData
import id.man.story.domain.usecase.NewsUseCase
import kotlinx.coroutines.launch

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
class MainVM(
    private val useCase: NewsUseCase
) : BaseListVM() {

    // region Attribute

    private val mutableUi = MutableLiveData<MainState>()
    val uiState = mutableUi as LiveData<MainState>
    private val _dataListId = mutableListOf<Int>()
    // endregion

    // region Get List Id Top Story

    fun getListIdTopStory(index: Int) {
        Log.d("WKWKWK", "CHECK $index")
        mutableUi.value = MainState.ShowLoading
        if (index == 0) {
            viewModelScope.launch {
                try {
                    val response = useCase.getListIdTopStory()
                    handleListId(response)
                } catch (t: Throwable) {
                    t.printStackTrace()
                }
            }
        } else {
            checkData(index)
        }
    }

    private fun handleListId(data: ResourceApi<List<Int>>) {
        if (data is ResourceApi.OnSuccess) {
            _dataListId.addAll(data.data)
            checkData(0)
        }
    }

    // endregion

    // region Get List Story

    private fun checkData(index: Int) {
        if (index != _dataListId.size - 1) {
            fetchData(index)
        } else {
            mutableUi.value = MainState.OnGetDataStory(listOf())
        }
    }

    private fun fetchData(index: Int) {
        val temp = _dataListId[index]
        viewModelScope.launch {
            val data = useCase.getDetailStory(temp)
            if (data is ResourceApi.OnSuccess) {
                mutableUi.value = MainState.OnGetDataStory(listOf(data.data))
            }
        }
    }

    // endregion

    // region Sealed UI State

    sealed class MainState {
        data class OnGetDataStory(val data: List<StoryData>) : MainState()
        object ShowLoading : MainState()
    }

    // endregion
}