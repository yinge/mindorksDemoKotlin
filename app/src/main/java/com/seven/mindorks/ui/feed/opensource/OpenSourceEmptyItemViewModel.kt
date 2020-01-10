package com.seven.mindorks.ui.feed.opensource

/**
 * at 2019/12/9
 * at 21:34
 * summary:
 */
class OpenSourceEmptyItemViewModel(listener: OpenSourceEmptyItemViewModelListener) {

    private val mListener: OpenSourceEmptyItemViewModelListener = listener

    fun onRetryClick(){
        mListener.onRetryClick()
    }

    interface OpenSourceEmptyItemViewModelListener {
        fun onRetryClick()
    }
}