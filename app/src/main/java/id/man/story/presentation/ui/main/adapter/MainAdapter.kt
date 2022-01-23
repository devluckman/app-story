package id.man.story.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.man.paging.BaseAdapterRecyclerView
import id.man.story.databinding.ItemStoryBinding
import id.man.story.domain.model.StoryData

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
class MainAdapter(
    private val onItemClick: (StoryData) -> Unit
) : BaseAdapterRecyclerView<StoryData, MainAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: ItemStoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: StoryData, onItemClick: (StoryData) -> Unit) = with(binding) {

            itemView.setOnClickListener {
                onItemClick(data)
            }

            tvStoryTitle.text = data.title
            tvStoryTotalComment.text = String.format("Comments (${data.commentsId.size})")
            tvStoryTotalScore.text = String.format("Score ${data.score}")
        }
    }

    override fun onCreateDefaultViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemStoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindDefaultViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getDataList()[position], onItemClick)
    }

}