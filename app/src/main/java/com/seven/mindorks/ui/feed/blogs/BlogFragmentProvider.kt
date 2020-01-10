package com.seven.mindorks.ui.feed.blogs

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * at 2019/12/3
 * at 10:42
 * summary:
 */
@Module
abstract class BlogFragmentProvider {
    @ContributesAndroidInjector(modules = [BlogFragmentModule::class])
    abstract fun provideBlogFragmentFactory(): BlogFragment
}