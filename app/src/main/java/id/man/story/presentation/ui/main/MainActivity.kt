package id.man.story.presentation.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.man.paging.BaseAdapterRecyclerView
import id.man.paging.DataListActivity
import id.man.paging.Resource
import id.man.story.databinding.ActivityMainBinding
import id.man.story.domain.model.StoryDataFirebase
import id.man.story.presentation.extentions.gone
import id.man.story.presentation.extentions.observeData
import id.man.story.presentation.extentions.visible
import id.man.story.presentation.ui.detail.DetailStoryActivity
import id.man.story.presentation.ui.main.adapter.FavoriteAdapter
import id.man.story.presentation.ui.main.adapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : DataListActivity<MainVM, ActivityMainBinding>() {

    // region Attribute

    override val viewModel: MainVM by viewModel()
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate
    private var index: Int = 0
    private val adapterFavorite = FavoriteAdapter()
    // endregion

    // region Initialize

    override fun initProcess() {
        enableInfiniteScrolling()
        setupRvFavorite()
    }

    // endregion

    // region Setup RV Favorite

    private fun setupRvFavorite() = with(binding) {
        rvStoryFavorite.layoutManager = getFlexLayoutManager()
        rvStoryFavorite.setHasFixedSize(true)
        rvStoryFavorite.adapter = adapterFavorite
        checkDataFavorite()
    }

    private fun getFlexLayoutManager(): FlexboxLayoutManager {
        val layoutManager = FlexboxLayoutManager(this).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
        }
        return layoutManager
    }


    private fun checkDataFavorite() = with(binding) {
        val ref = FirebaseDatabase.getInstance().reference
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listFavorite = mutableListOf<StoryDataFirebase>()
                val data = dataSnapshot.children
                data.forEach {
                    it.key?.let { key ->
                        dataSnapshot.child(key).getValue(StoryDataFirebase::class.java)
                            ?.let { data ->
                                listFavorite.add(data)
                            }
                    }
                }
                if (listFavorite.isEmpty()) {
                    clFavorite.gone()
                } else {
                    clFavorite.visible()
                    adapterFavorite.addData(listFavorite.toList())
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        ref.addValueEventListener(listener)
    }

    // endregion

    // region Subscriptions

    override fun initSubscription() = with(viewModel) {
        observeData(uiState) {
            when (it) {
                is MainVM.MainState.OnGetDataStory -> {
                    onGetContent(it.data)
                }
                MainVM.MainState.ShowLoading -> {
                    updateResource(Resource.loading())
                }
            }
        }
    }

    // endregion


    // region Listener

    override fun onActionListener() = with(binding) {

    }


    // endregion

    // region Setup Component Pagination

    @Suppress("UNCHECKED_CAST")
    override fun initRecyclerAdapter(): BaseAdapterRecyclerView<Any, RecyclerView.ViewHolder> {
        return MainAdapter {
            DetailStoryActivity.newInstance(this, it)
        } as BaseAdapterRecyclerView<Any, RecyclerView.ViewHolder>
    }

    override fun initRecyclerLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun initRecyclerView(): RecyclerView = binding.recyclerDataList

    override fun initSwipeRefreshLayout(): SwipeRefreshLayout = binding.swipeRefreshDataList


    // endregion

    // region Fetch Data

    override fun fetchData() {
        viewModel.getListIdTopStory(index)
    }

    private fun onGetContent(data: List<Any>) {
        updateResource(Resource.success(data))
        enableSwipeToRefresh()
        if (data.isEmpty()) {
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

}