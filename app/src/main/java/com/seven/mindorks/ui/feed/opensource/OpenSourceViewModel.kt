package com.seven.mindorks.ui.feed.opensource

import androidx.lifecycle.MutableLiveData
import com.seven.mindorks.data.DataManager
import com.seven.mindorks.data.model.api.OpenSourceResponse
import com.seven.mindorks.ui.base.BaseViewModel
import com.seven.mindorks.utils.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single

/**
 * at 2019/12/3
 * at 14:08
 * summary:
 */
class OpenSourceViewModel constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<OpenSourceNavigator>(dataManager, schedulerProvider) {

    var openSourceItemsLiveData: MutableLiveData<List<OpenSourceItemViewModel>> = MutableLiveData()

    init {
        fetchRepos()
    }

    fun fetchRepos() {
        setIsLoading(true)
        mCompositeDisposable.add(
            dataManager.getOpenSourceApiCall()
                .map { it.data }
                .flatMap(this::getViewModelList)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    openSourceItemsLiveData.value = it
                    setIsLoading(false)
                }, {
                    setIsLoading(false)
                    mNavigator.get()?.handleError(it)
                })
        )
    }

    private fun getViewModelList(repoList: List<OpenSourceResponse.Repo>): Single<List<OpenSourceItemViewModel>> {
        return Observable.fromIterable(repoList)
            .map {
                OpenSourceItemViewModel(
                    it.coverImgUrl,
                    it.title,
                    it.description,
                    it.projectUrl
                )
            }.toList()
    }
}