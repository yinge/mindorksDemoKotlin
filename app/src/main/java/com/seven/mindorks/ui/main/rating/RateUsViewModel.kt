package com.seven.mindorks.ui.main.rating

import com.seven.mindorks.data.DataManager
import com.seven.mindorks.ui.base.BaseViewModel
import com.seven.mindorks.utils.rx.SchedulerProvider

/**
 * at 2019/12/4
 * at 15:12
 * summary:
 */
class RateUsViewModel constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<RateUsCallback>(dataManager, schedulerProvider) {
    fun onLaterClick() {
        mNavigator.get()?.dismissDialog()
    }

    fun onSubmitClick() {
        mNavigator.get()?.dismissDialog()
    }
}