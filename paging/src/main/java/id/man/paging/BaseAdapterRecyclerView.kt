package id.man.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import id.man.paging.databinding.BaseAdapterRecyclerEmptyBinding
import id.man.paging.databinding.BaseAdapterRecyclerErrorBinding
import id.man.paging.databinding.BaseAdapterRecyclerErrorPaginationBinding

/**
 *
 * Created by Lukmanul Hakim on  1/14/2022
 * devs.lukman@gmail.com
 */
abstract class BaseAdapterRecyclerView<DATA, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /** The data set for this adapter */
    private var dataList: MutableList<DATA> = ArrayList()

    /** @return the data set for this adapter */
    fun getDataList(): MutableList<DATA> {
        return dataList
    }

    /**
     * Sets the data set for this adapter.
     * @param list the new list
     */
    fun setDataList(list: MutableList<DATA>?) {
        dataList.clear()
        if (list != null) dataList.addAll(list)
    }

    /** Sets the data set for this adapter  */
    fun clear() {
        dataList.clear()
    }

    /** The content view type for this adapter */
    private var contentViewType: Int = NULL

    /** @return the content view type for this adapter */
    fun getContentViewType(): Int {
        return contentViewType
    }

    /**
     * Sets the content view type for this adapter.
     * @param viewType the content view type
     * @return true if the content view is changed
     */
    fun setContentViewType(viewType: Int): Boolean {
        val isChanged = contentViewType != viewType
        contentViewType = viewType
        return isChanged
    }

    /**
     * The empty text for this adapter.
     * Empty text will only be displayed on [EMPTY].
     */
    var emptyText: String? = null

    /**
     * The error text for this adapter.
     * Error text will only be displayed on [ERROR] and [ERROR_PAGINATION].
     */
    var errorText: String? = null

    /** The listener for retry button */
    var retryListener: OnRetryListener? = null

    override fun getItemViewType(position: Int): Int {
        if (position < dataList.size) return DEFAULT
        if (dataList.size > 0) {
            return when (contentViewType) {
                PROGRESS -> PROGRESS_PAGINATION
                ERROR -> ERROR_PAGINATION
                else -> contentViewType
            }
        }
        return contentViewType
    }

    override fun getItemCount(): Int {
        return dataList.size + 1
    }

    @CallSuper
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            NULL -> onCreateNullViewHolder(parent)
            EMPTY -> onCreateEmptyViewHolder(parent)
            PROGRESS -> onCreateProgressViewHolder(parent)
            ERROR -> onCreateErrorViewHolder(parent)
            PROGRESS_PAGINATION -> onCreateProgressPaginationViewHolder(parent)
            ERROR_PAGINATION -> onCreateErrorPaginationViewHolder(parent)
            else -> onCreateDefaultViewHolder(parent, viewType)
        }
    }

    @Suppress("UNCHECKED_CAST")
    @CallSuper
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            NULL -> onBindNullViewHolder()
            EMPTY -> onBindEmptyViewHolder(holder)
            PROGRESS -> onBindProgressViewHolder(holder)
            ERROR -> onBindErrorViewHolder(holder)
            PROGRESS_PAGINATION -> onBindProgressPaginationViewHolder(holder)
            ERROR_PAGINATION -> onBindErrorPaginationViewHolder(holder)
            else -> onBindDefaultViewHolder(holder as VH, position)
        }
    }

    /** Creates the [NullViewHolder]  */
    fun onCreateNullViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NullViewHolder<Any?, Any?>(
            inflater.inflate(
                R.layout.base_adapter_recycler_null,
                parent,
                false
            )
        )
    }

    /** Creates the [EmptyViewHolder]  */
    open fun onCreateEmptyViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = BaseAdapterRecyclerEmptyBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return EmptyViewHolder<Any?, Any?>(binding)
    }

    /** Creates the [ProgressViewHolder]  */
    open fun onCreateProgressViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProgressViewHolder<Any?, Any?>(
            inflater.inflate(
                R.layout.base_adapter_recycler_progress,
                parent,
                false
            )
        )
    }

    /** Creates the [ErrorViewHolder]  */
    open fun onCreateErrorViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = BaseAdapterRecyclerErrorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ErrorViewHolder<Any?, Any?>(binding)
    }

    /** Creates the [ProgressPaginationViewHolder]  */
    open fun onCreateProgressPaginationViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProgressPaginationViewHolder<Any?, Any?>(
            inflater.inflate(
                R.layout.base_adapter_recycler_progress_pagination,
                parent,
                false
            )
        )
    }

    /** Creates the [ErrorPaginationViewHolder]  */
    open fun onCreateErrorPaginationViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = BaseAdapterRecyclerErrorPaginationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ErrorPaginationViewHolder<Any?, Any?>(binding)
    }


    /** Binds the [NullViewHolder]  */
    fun onBindNullViewHolder() {
        // Do nothing
    }

    /** Binds the [EmptyViewHolder]  */
    @Suppress("UNCHECKED_CAST")
    open fun onBindEmptyViewHolder(holder: RecyclerView.ViewHolder) {
        (holder as EmptyViewHolder<*, *>).bindView()
    }

    /** Binds the [ProgressViewHolder]  */
    open fun onBindProgressViewHolder(holder: RecyclerView.ViewHolder) {
        // Do nothing
    }

    /** Binds the [ErrorViewHolder]  */
    @Suppress("UNCHECKED_CAST")
    open fun onBindErrorViewHolder(holder: RecyclerView.ViewHolder) {
        (holder as ErrorViewHolder<*, *>).bindView()
    }

    /** Binds the [ProgressPaginationViewHolder]  */
    open fun onBindProgressPaginationViewHolder(holder: RecyclerView.ViewHolder) {
        // Do nothing
    }

    /** Binds the [ErrorPaginationViewHolder]  */
    @Suppress("UNCHECKED_CAST")
    open fun onBindErrorPaginationViewHolder(holder: RecyclerView.ViewHolder) {
        (holder as ErrorPaginationViewHolder<*, *>).bindView()
    }

    /** The null [RecyclerView.ViewHolder]  */
    inner class NullViewHolder<T, U>(itemView: View) : RecyclerView.ViewHolder(itemView)

    /** The empty [RecyclerView.ViewHolder]  */
    inner class EmptyViewHolder<T, U>(val binding: BaseAdapterRecyclerEmptyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView() = with(binding) {
            if (emptyText == null || emptyText!!.isEmpty()) emptyText = "No Data"
            txtEmpty.text = emptyText
        }
    }


    /** The progress [RecyclerView.ViewHolder]  */
    inner class ProgressViewHolder<T, U>(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    /** The error [RecyclerView.ViewHolder]  */
    inner class ErrorViewHolder<T, U>(val binding: BaseAdapterRecyclerErrorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView() = with(binding) {
            txtError.text = errorText
            if (errorText == null || errorText!!.isEmpty())
                txtError.visibility = View.GONE
            else
                txtError.visibility = View.VISIBLE

            btnRetry.setOnClickListener {
                retryListener?.onErrorRetryClicked()
            }
        }
    }

    /** The progress [RecyclerView.ViewHolder]  */
    inner class ProgressPaginationViewHolder<T, U>(itemView: View) :
        RecyclerView.ViewHolder(itemView) {}

    /** The error [RecyclerView.ViewHolder]  */
    inner class ErrorPaginationViewHolder<T, U>(val binding: BaseAdapterRecyclerErrorPaginationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView() = with(binding) {
            txtErrorPagination.text = errorText

            if (errorText == null || errorText!!.isEmpty())
                txtErrorPagination.visibility = View.GONE
            else
                txtErrorPagination.visibility = View.VISIBLE

            itemView.setOnClickListener {
                retryListener?.onPaginationErrorRetryClicked()
            }
        }
    }

    open fun modifyItem(position: Int, model: DATA) {
        dataList.set(position, model)
        notifyItemChanged(position)
    }

    /** Create the [RecyclerView.ViewHolder] for normal layout  */
    abstract fun onCreateDefaultViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder

    /** Binds the [RecyclerView.ViewHolder] for normal layout  */
    abstract fun onBindDefaultViewHolder(holder: VH, position: Int)

    /** The interface for retry functions  */
    interface OnRetryListener {

        /** Called when the retry button on the error layout is clicked  */
        fun onErrorRetryClicked()

        /** Called when the retry button on the pagination error layout is clicked  */
        fun onPaginationErrorRetryClicked()
    }

    companion object {

        const val DEFAULT = 1000
        const val NULL = 2000
        const val EMPTY = 2001
        const val PROGRESS = 2002
        const val ERROR = 2003
        private const val PROGRESS_PAGINATION = 3000
        private const val ERROR_PAGINATION = 3001
    }
}