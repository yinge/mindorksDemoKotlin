package com.seven.mindorks.ui.feed

import com.seven.mindorks.data.DataManager
import com.seven.mindorks.ui.base.BaseViewModel
import com.seven.mindorks.utils.rx.SchedulerProvider

/**
 * at 2019/12/2
 * at 17:49
 * summary:
 */
class FeedViewModel constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<Void>(dataManager, schedulerProvider) {

}