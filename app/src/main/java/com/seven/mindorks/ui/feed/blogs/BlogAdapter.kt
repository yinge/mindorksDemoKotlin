package com.seven.mindorks.ui.feed.blogs

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seven.mindorks.data.model.api.BlogResponse
import com.seven.mindorks.databinding.ItemBlogEmptyViewBinding
import com.seven.mindorks.databinding.ItemBlogViewBinding
import com.seven.mindorks.ui.base.BaseViewHolder

/**
 * at 2019/12/3
 * at 10:47
 * summary:
 */
class BlogAdapter(private var mBlogResponseList: MutableList<BlogResponse.Blog>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    private lateinit var mListener: BlogAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val binding =
                    ItemBlogViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BlogViewHolder(binding)
            }
            VIEW_TYPE_EMPTY -> {
                val binding = ItemBlogEmptyViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                EmptyViewHolder(binding)
            }
            else -> {
                val binding = ItemBlogEmptyViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                EmptyViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int =
        if (mBlogResponseList != null && mBlogResponseList.size > 0) mBlogResponseList.size
        else 1

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (mBlogResponseList != null && mBlogResponseList.isNotEmpty()) VIEW_TYPE_NORMAL else VIEW_TYPE_EMPTY
    }

    fun addItems(blogList: List<BlogResponse.Blog>?) {
        blogList?.let {
            mBlogResponseList.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun clearItems() {
        mBlogResponseList.clear()
    }

    fun setListener(listener: BlogAdapterListener) {
        mListener = listener
    }

    interface BlogAdapterListener {
        fun onRetryClick()
    }

    inner class BlogViewHolder(private val binding: ItemBlogViewBinding) :
        BaseViewHolder(binding.root),
        BlogItemViewModel.BlogItemViewModelListener {

        private lateinit var mBlogItemViewModel: BlogItemViewModel

        override fun onBind(position: Int) {
            val blog: BlogResponse.Blog = mBlogResponseList[position]
            mBlogItemViewModel = BlogItemViewModel(blog, this)
            binding.viewModel = mBlogItemViewModel
            binding.executePendingBindings()
        }

        override fun onItemClick(blogUrl: String?) {
            blogUrl?.let {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.addCategory(Intent.CATEGORY_BROWSABLE)
                intent.data = Uri.parse(blogUrl)
                itemView.context.startActivity(intent)
            }
        }

    }

    inner class EmptyViewHolder(private val binding: ItemBlogEmptyViewBinding) :
        BaseViewHolder(binding.root),
        BlogEmptyItemViewModel.BlogEmptyItemViewModelListener {
        override fun onBind(position: Int) {
            val emptyItemViewModel = BlogEmptyItemViewModel(this)
            binding.viewModel = emptyItemViewModel
        }

        override fun onRetryClick() {
            mListener.onRetryClick()
        }

    }

    companion object {
        const val VIEW_TYPE_EMPTY = 0

        const val VIEW_TYPE_NORMAL = 1
    }
}