package com.seven.mindorks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.seven.mindorks.data.DataManager
import com.seven.mindorks.ui.about.AboutViewModel
import com.seven.mindorks.ui.feed.FeedViewModel
import com.seven.mindorks.ui.feed.blogs.BlogViewModel
import com.seven.mindorks.ui.feed.opensource.OpenSourceViewModel
import com.seven.mindorks.ui.login.LoginViewModel
import com.seven.mindorks.ui.main.MainViewModel
import com.seven.mindorks.ui.main.rating.RateUsViewModel
import com.seven.mindorks.ui.splash.SplashViewModel
import com.seven.mindorks.utils.rx.SchedulerProvider
import javax.inject.Inject

/**
 * at 2019/12/3
 * at 14:49
 * summary:
 */
class ViewModelProviderFactory @Inject constructor(
    private val dataManager: DataManager,
    private val schedulerProvider: SchedulerProvider
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(AboutViewModel::class.java) -> {
                return AboutViewModel(dataManager, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(FeedViewModel::class.java) -> {
                return FeedViewModel(dataManager, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                return LoginViewModel(dataManager, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                return MainViewModel(dataManager, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(BlogViewModel::class.java) -> {
                return BlogViewModel(dataManager, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(RateUsViewModel::class.java) -> {
                return RateUsViewModel(dataManager, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(OpenSourceViewModel::class.java) -> {
                return OpenSourceViewModel(dataManager, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                return SplashViewModel(dataManager, schedulerProvider) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}