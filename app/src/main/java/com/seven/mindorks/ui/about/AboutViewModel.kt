package com.seven.mindorks.ui.about

import com.seven.mindorks.data.DataManager
import com.seven.mindorks.ui.base.BaseViewModel
import com.seven.mindorks.utils.rx.SchedulerProvider

/**
 * at 2019/12/3
 * at 14:24
 * summary:
 */
class AboutViewModel constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<AboutNavigator>(dataManager, schedulerProvider) {

    fun onNavBackClick() {
        mNavigator.get()?.goBack()
    }
}