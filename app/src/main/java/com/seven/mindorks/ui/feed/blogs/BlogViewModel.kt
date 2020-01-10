package com.seven.mindorks.ui.feed.blogs

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.seven.mindorks.data.DataManager
import com.seven.mindorks.data.model.api.BlogResponse
import com.seven.mindorks.ui.base.BaseViewModel
import com.seven.mindorks.utils.rx.SchedulerProvider

/**
 * at 2019/12/3
 * at 14:12
 * summary:
 */
class BlogViewModel constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<BlogNavigator>(dataManager, schedulerProvider) {

    var blogListLiveData: MutableLiveData<List<BlogResponse.Blog>> = MutableLiveData()

    init {
        fetchBlogs()
    }

    fun fetchBlogs() {
        setIsLoading(true)
        mCompositeDisposable.add(
            dataManager.getBlogApiCall()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({

                    it?.data?.let {
                        blogListLiveData.value = it
                    }
                    setIsLoading(false)
                }, {
                    setIsLoading(false)
                    mNavigator.get()?.handleError(it)
                })
        )
    }
}