package com.seven.mindorks.ui.main

/**
 * at 2019/12/4
 * at 16:27
 * summary:
 */
interface MainNavigator {
    fun handleError(throwable: Throwable)

    fun openLoginActivity()
}