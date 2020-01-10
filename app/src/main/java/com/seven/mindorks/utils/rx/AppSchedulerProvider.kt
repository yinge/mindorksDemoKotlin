package com.seven.mindorks.utils.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * at 2019/12/2
 * at 16:34
 * summary:
 */
class AppSchedulerProvider : SchedulerProvider {
    override fun computation(): Scheduler? = Schedulers.computation()

    override fun io(): Scheduler? = Schedulers.io()

    override fun ui(): Scheduler? = AndroidSchedulers.mainThread()
}