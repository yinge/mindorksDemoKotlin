package com.seven.mindorks.ui.feed.opensource

import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides

/**
 * at 2019/12/3
 * at 11:46
 * summary:
 */
@Module
class OpenSourceFragmentModule {
    @Provides
    fun provideLinearLayoutManager(fragment: OpenSourceFragment): LinearLayoutManager =
        LinearLayoutManager(fragment.activity)

    @Provides
    fun provideOpenSourceAdapter(): OpenSourceAdapter =
        OpenSourceAdapter()

}