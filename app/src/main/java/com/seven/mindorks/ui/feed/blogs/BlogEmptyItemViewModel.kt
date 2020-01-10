package com.seven.mindorks.ui.feed.blogs

/**
 * at 2019/12/9
 * at 23:24
 * summary:
 */
class BlogEmptyItemViewModel(listener: BlogEmptyItemViewModelListener) {

    private val mListener: BlogEmptyItemViewModelListener = listener

    fun onRetryClick() {
        mListener.onRetryClick()
    }

    interface BlogEmptyItemViewModelListener {
        fun onRetryClick()
    }
}