package id.man.story.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.man.story.databinding.ItemFavoriteBinding
import id.man.story.domain.model.StoryDataFirebase

/**
 *
 * Created by Lukmanul Hakim on  1/23/2022
 * devs.lukman@gmail.com
 */
class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private val data = mutableListOf<StoryDataFirebase>()

    class ViewHolder(
        private val binding: ItemFavoriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: StoryDataFirebase?) = with(binding) {
            tvTitle.text = data?.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun addData(newData: List<StoryDataFirebase>) {
        data.addAll(newData)
        notifyItemInserted(data.size - 1)
    }
}