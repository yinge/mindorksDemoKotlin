package com.seven.mindorks.ui.feed

import dagger.Module
import dagger.Provides

/**
 * at 2019/12/3
 * at 10:38
 * summary:
 */
@Module
class FeedActivityModule {
    @Provides
    fun provideFeedPagerAdapter(activity: FeedActivity): FeedPagerAdapter =
        FeedPagerAdapter(activity.supportFragmentManager)
}