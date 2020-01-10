package com.seven.mindorks.ui.about

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * at 2019/12/3
 * at 14:21
 * summary:
 */
@Module
abstract class AboutFragmentProvider {
    @ContributesAndroidInjector
    abstract fun provideAboutFragmentFactory(): AboutFragment
}