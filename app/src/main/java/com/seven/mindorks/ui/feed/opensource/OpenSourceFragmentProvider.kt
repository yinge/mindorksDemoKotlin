package com.seven.mindorks.ui.feed.opensource

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * at 2019/12/3
 * at 11:27
 * summary:
 */
@Module
abstract class OpenSourceFragmentProvider {
    @ContributesAndroidInjector(modules = [OpenSourceFragmentModule::class])
    abstract fun provideOpenSourceFragmentFactory(): OpenSourceFragment

}