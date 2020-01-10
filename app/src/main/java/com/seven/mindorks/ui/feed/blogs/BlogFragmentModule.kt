package com.seven.mindorks.ui.feed.blogs

import androidx.recyclerview.widget.LinearLayoutManager
import com.seven.mindorks.data.model.api.BlogResponse
import dagger.Module
import dagger.Provides

/**
 * at 2019/12/3
 * at 10:45
 * summary:
 */
@Module
class BlogFragmentModule {
    @Provides
    fun provideBlogAdapter(): BlogAdapter = BlogAdapter(ArrayList())

    @Provides
    fun provideLinearLayoutManager(fragment: BlogFragment): LinearLayoutManager =
        LinearLayoutManager(fragment.activity)
}