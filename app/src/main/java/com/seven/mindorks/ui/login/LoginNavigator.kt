package com.seven.mindorks.ui.login

/**
 * at 2019/12/4
 * at 17:32
 * summary:
 */
interface LoginNavigator {
    fun handleError(throwable: Throwable)

    fun login()

    fun openMainActivity()
}