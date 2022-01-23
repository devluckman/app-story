package id.man.story.presentation.ui.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.man.paging.BaseAdapterRecyclerView
import id.man.paging.DataListActivity
import id.man.paging.Resource
import id.man.story.R
import id.man.story.databinding.ActivityDetailStoryBinding
import id.man.story.domain.model.StoryData
import id.man.story.domain.model.StoryData.Companion.toFirebase
import id.man.story.domain.model.StoryDataFirebase
import id.man.story.presentation.extentions.observeData
import id.man.story.presentation.ui.detail.adapter.CommentAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailStoryActivity : DataListActivity<DetailStoryVM, ActivityDetailStoryBinding>() {

    // region Attribute

    override val viewModel: DetailStoryVM by viewModel()
    override val bindingInflater: (LayoutInflater) -> ActivityDetailStoryBinding
        get() = ActivityDetailStoryBinding::inflate
    private var index: Int = 0
    private var isFavorite = false
    private val ref = FirebaseDatabase.getInstance().reference
    private var kids = mutableListOf<Int>()
    // endregion

    // region Initialize

    override fun initProcess() {
        val data = intent.getParcelableExtra<StoryData>(KEY_DATA_STORY)
        data?.let { setDataToView(it) }
        enableInfiniteScrolling()
    }

    // endregion

    // region Subscriptions

    override fun initSubscription() = with(viewModel) {
        observeData(comments, ::onGetContent)
    }

    // endregion


    // region Listener

    override fun onActionListener() = with(binding) {

    }


    // endregion

    // region Setup Component Pagination

    @Suppress("UNCHECKED_CAST")
    override fun initRecyclerAdapter(): BaseAdapterRecyclerView<Any, RecyclerView.ViewHolder> {
        return CommentAdapter() as BaseAdapterRecyclerView<Any, RecyclerView.ViewHolder>
    }

    override fun initRecyclerLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun initRecyclerView(): RecyclerView = binding.recyclerDataList

    override fun initSwipeRefreshLayout(): SwipeRefreshLayout = binding.swipeRefreshDataList


    // endregion

    // region Fetch Data

    override fun fetchData() {
        if (kids.isNotEmpty()) {
            viewModel.getComment(kids[index])
        } else {
            onGetContent(listOf())
            disableInfiniteScrolling()
        }
    }

    private fun onGetContent(data: List<Any>) {
        updateResource(Resource.success(data))
        enableSwipeToRefresh()
        if (index == kids.size - 1) {
            disableInfiniteScrolling()
        } else {
            index = index.plus(1)
            enableInfiniteScrolling()
        }
    }

    override fun onSwipeToRefresh() {
        refreshList()
        enableInfiniteScrolling()
        fetchData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refreshList() {
        getAdapter().clear()
        getAdapter().notifyDataSetChanged()

        index = 1
    }

    // endregion

    // region View Ready

    private fun setDataToView(data: StoryData) = with(binding) {
        Log.d("WKWKWK", "CHECK KIDS ${data.commentsId}")
        tvStoryTitle.text = data.title
        tvStoryName.text = String.format("By ${data.by}")
        tvStoryDate.text = data.time
        kids.addAll(data.commentsId)
        checkFavorite(data.id.toString())

        ivStoryFavorite.setOnClickListener {
            updateFavorite(data)
        }

    }

    // endregion

    // region Favorite

    private fun updateFavorite(data: StoryData) {
        if (isFavorite) {
            deleteFavorite(data.id.toString())
        } else {
            setFavorite(data)
        }
    }

    private fun setFavorite(data: StoryData) {
        ref.child(data.id.toString()).setValue(data.toFirebase())
        checkFavorite(data.id.toString())
    }

    private fun deleteFavorite(id: String) {
        val reference = ref.child(id)
        reference.removeValue()
        checkFavorite(id)
    }

    private fun checkFavorite(id: String) {

        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val data = dataSnapshot.getValue(StoryDataFirebase::class.java)
                stateFavorite(data != null)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        ref.child(id).addValueEventListener(listener)
    }

    private fun stateFavorite(state: Boolean) = with(binding) {
        if (state) ivStoryFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                this@DetailStoryActivity,
                R.drawable.ic_favorite
            )
        ) else ivStoryFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                this@DetailStoryActivity,
                R.drawable.ic_unfavorite
            )
        )
        isFavorite = state
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