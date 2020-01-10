package com.seven.mindorks.ui.feed.blogs

import androidx.databinding.ObservableField
import com.seven.mindorks.data.model.api.BlogResponse.Blog

/**
 * at 2019/12/6
 * at 20:42
 * summary:
 */
class BlogItemViewModel constructor(
    blog: Blog, listener: BlogItemViewModelListener
) {
    private val mBlog: Blog = blog

    private val mListener: BlogItemViewModelListener = listener

    val author: ObservableField<String>

    val content: ObservableField<String>

    val date: ObservableField<String>

    val imageUrl: ObservableField<String>

    val title: ObservableField<String>

    init {
        imageUrl = ObservableField(mBlog.coverImgUrl)
        title = ObservableField(mBlog.title)
        author = ObservableField(mBlog.author)
        date = ObservableField(mBlog.date)
        content = ObservableField(mBlog.description)
    }


    fun onItemClick() {
        mListener.onItemClick(mBlog.blogUrl)
    }

    interface BlogItemViewModelListener {
        fun onItemClick(blogUrl: String?)
    }
}