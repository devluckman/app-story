package id.man.story.presentation.ui.main

import android.view.LayoutInflater
import id.man.story.databinding.ActivityMainBinding
import id.man.story.domain.model.StoryData
import id.man.story.presentation.base.BaseActivity
import id.man.story.presentation.extentions.observeData
import id.man.story.presentation.ui.detail.DetailStoryActivity
import id.man.story.presentation.ui.main.adapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainVM, ActivityMainBinding>() {

    // region Attribute

    override val vm: MainVM by viewModel()
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate
    private val adapter = MainAdapter(::onDetail)
    // endregion

    // region View Ready

    override fun onViewReady() = with(binding) {
        mainRvStory.adapter = adapter
        onSubscription()
        vm.getListIdTopStory()
    }

    private fun onDetail(data: StoryData) {
        DetailStoryActivity.newInstance(this, data)
    }

    // endregion

    // region Subscription

    private fun onSubscription() = with(vm) {

        observeData(uiState) {
            when (it) {
                is MainVM.MainState.OnGetDataStory -> {
                    adapter.updateData(it.data)
                }
                is MainVM.MainState.OnSetProgress -> {
                    // CommonHelper.setProgress(it.percent)
                }
                MainVM.MainState.ShowLoading -> {
                    // CommonHelper.showProgress(this@MainActivity)
                }
                MainVM.MainState.IsFetchDataDone -> {
                    // CommonHelper.hideProgress()
                }
            }
        }
    }

    // endregion

}