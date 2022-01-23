package id.man.story.presentation.ui.detail.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.man.paging.BaseAdapterRecyclerView
import id.man.story.databinding.ItemCommentOnStoryBinding
import id.man.story.domain.model.CommentData

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
class CommentAdapter : BaseAdapterRecyclerView<CommentData, CommentAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: ItemCommentOnStoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CommentData) = with(binding) {

            tvStoryComment.text = data.text

        }

    }

    override fun onCreateDefaultViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemCommentOnStoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindDefaultViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getDataList()[position])
    }

}