package com.seven.mindorks.ui.feed.blogs

import com.seven.mindorks.data.model.api.BlogResponse

/**
 * at 2019/12/4
 * at 16:13
 * summary:
 */
interface BlogNavigator {
    fun handleError(throwable: Throwable)

    fun updateBlog(blogList: List<BlogResponse.Blog>)
}