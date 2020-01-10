package com.seven.mindorks.ui.splash

import com.seven.mindorks.data.DataManager
import com.seven.mindorks.ui.base.BaseViewModel
import com.seven.mindorks.utils.rx.SchedulerProvider

/**
 * at 2019/12/3
 * at 14:41
 * summary:
 */
class SplashViewModel constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<SplashNavigator>(dataManager, schedulerProvider) {

    fun startSeeding() {
        mCompositeDisposable.add(
            dataManager.seedDatabaseQuestions()
                .flatMap { dataManager.seedDatabaseOptions() }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ decideNextActivity() }, { decideNextActivity() })
        )
    }

    private fun decideNextActivity() {
        if (dataManager.getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.mType) {
            mNavigator.get()?.openLoginActivity()
        } else {
            mNavigator.get()?.openMainActivity()
        }
    }
}