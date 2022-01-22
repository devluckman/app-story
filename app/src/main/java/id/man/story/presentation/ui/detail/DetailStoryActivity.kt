package id.man.story.presentation.ui.detail

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import id.man.story.databinding.ActivityDetailStoryBinding
import id.man.story.domain.model.StoryData
import id.man.story.presentation.base.BaseActivity
import id.man.story.presentation.extentions.observeData
import id.man.story.presentation.ui.detail.adapter.CommentAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailStoryActivity : BaseActivity<DetailStoryVM, ActivityDetailStoryBinding>() {

    // region Attribute

    override val vm: DetailStoryVM by viewModel()
    override val bindingInflater: (LayoutInflater) -> ActivityDetailStoryBinding
        get() = ActivityDetailStoryBinding::inflate
    private val adapter = CommentAdapter()

    // endregion

    // region View Ready

    override fun onViewReady() {
        val data = intent.getParcelableExtra<StoryData>(KEY_DATA_STORY)
        data?.let { setDataToView(it) }
        onSubscription()
        onActionListener()
    }

    private fun setDataToView(data: StoryData) = with(binding) {
        tvStoryTitle.text = data.title
        tvStoryName.text = String.format("By ${data.by}")
        tvStoryDate.text = data.time
        rvStoryComment.adapter = adapter

        val id = 2921983
        vm.getComment(id)
    }

    private fun onSubscription() = with(vm) {
        observeData(comments, adapter::submitList)
    }

    private fun onActionListener() = with(binding) {

        ivStoryFavorite.setOnClickListener {

        }

    }

    // endregion

    // region companion

    companion object {
        private const val KEY_DATA_STORY = "KEY_DATA_STORY"
        fun newInstance(context: Activity, data: StoryData) {
            val intent = Intent(context, DetailStoryActivity::class.java)
            intent.putExtra(KEY_DATA_STORY, data)
            context.startActivity(intent)
        }

    }

    // endregion

}