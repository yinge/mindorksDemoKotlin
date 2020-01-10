package com.seven.mindorks.di.builder

import com.seven.mindorks.ui.about.AboutFragmentProvider
import com.seven.mindorks.ui.feed.FeedActivity
import com.seven.mindorks.ui.feed.FeedActivityModule
import com.seven.mindorks.ui.feed.blogs.BlogFragmentModule
import com.seven.mindorks.ui.feed.blogs.BlogFragmentProvider
import com.seven.mindorks.ui.feed.opensource.OpenSourceFragmentProvider
import com.seven.mindorks.ui.login.LoginActivity
import com.seven.mindorks.ui.main.MainActivity
import com.seven.mindorks.ui.main.rating.RateUsDialogProvider
import com.seven.mindorks.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * at 2019/12/2
 * at 14:26
 * summary:
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(
        modules = [
            FeedActivityModule::class,
            BlogFragmentProvider::class,
            OpenSourceFragmentProvider::class
        ]
    )
    abstract fun bindFeedActivity(): FeedActivity

    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(
        modules = [
            AboutFragmentProvider::class,
            RateUsDialogProvider::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity
}