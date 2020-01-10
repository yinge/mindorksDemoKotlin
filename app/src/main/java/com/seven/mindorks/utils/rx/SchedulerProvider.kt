package com.seven.mindorks.utils.rx

import io.reactivex.Scheduler

/**
 * at 2019/12/2
 * at 16:20
 * summary:
 */
interface SchedulerProvider {
    fun computation(): Scheduler?

    fun io(): Scheduler?

    fun ui(): Scheduler?
}