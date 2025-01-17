package com.seven.mindorks.utils

import com.seven.mindorks.BuildConfig
import timber.log.Timber


/**
 * at 2019/12/5
 * at 11:28
 * summary:
 */
object AppLogger {
    fun init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun d(s: String, vararg objects: Any) {
        Timber.d(s, objects)
    }

    fun d(throwable: Throwable, s: String, vararg objects: Any) {
        Timber.d(throwable, s, objects)
    }

    fun e(s: String, vararg objects: Any) {
        Timber.e(s, objects)
    }

    fun e(throwable: Throwable, s: String, vararg objects: Any) {
        Timber.e(throwable, s, objects)
    }

    fun i(s: String, vararg objects: Any) {
        Timber.i(s, objects)
    }

    fun i(throwable: Throwable, s: String, vararg objects: Any) {
        Timber.i(throwable, s, objects)
    }

    fun w(s: String, vararg objects: Any) {
        Timber.w(s, objects)
    }

    fun w(throwable: Throwable, s: String, vararg objects: Any) {
        Timber.w(throwable, s, objects)
    }
}