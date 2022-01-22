package id.man.story.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.man.story.databinding.ItemStoryBinding
import id.man.story.domain.model.StoryData

/**
 *
 * Created by Lukmanul Hakim on  1/22/2022
 * devs.lukman@gmail.com
 */
class MainAdapter(
    private val onItemClick: (StoryData) -> Unit
) : ListAdapter<StoryData, MainAdapter.ViewHolder>(StoryDiffUtil()) {
    val data = mutableListOf<StoryData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

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

    fun updateData(newData: List<StoryData>) {
        data.addAll(newData)
        submitList(data)
    }

    class StoryDiffUtil : DiffUtil.ItemCallback<StoryData>() {
        override fun areItemsTheSame(oldItem: StoryData, newItem: StoryData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: StoryData, newItem: StoryData): Boolean {
            return oldItem == newItem
        }
    }
}