package com.seven.mindorks.ui.login

import com.seven.mindorks.data.DataManager
import com.seven.mindorks.data.model.api.LoginRequest
import com.seven.mindorks.ui.base.BaseViewModel
import com.seven.mindorks.utils.CommonUtils
import com.seven.mindorks.utils.rx.SchedulerProvider

/**
 * at 2019/12/3
 * at 9:56
 * summary:
 */
class LoginViewModel constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<LoginNavigator>(dataManager, schedulerProvider) {

    fun isEmailAndPasswordValid(email: String?, password: String?): Boolean {
        if (email.isNullOrEmpty()) {
            return false
        }
        if (!CommonUtils.isEmailValid(email)) {
            return false
        }
        if (password.isNullOrEmpty()) {
            return false
        }
        return true
    }

    fun login(email: String, password: String) {
        setIsLoading(true)
        mCompositeDisposable.add(
            dataManager.doServerLoginApiCall(LoginRequest.ServerLoginRequest(email, password))
                .doOnSuccess {
                    dataManager.updateUserInfo(
                        it.accessToken, it.userId, DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                        it.userName, it.userEmail, it.googleProfilePicUrl
                    )
                }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    setIsLoading(false)
                    mNavigator.get()?.openMainActivity()
                }, {
                    setIsLoading(false)
                    mNavigator.get()?.handleError(it)
                })
        )
    }

    fun onFbLoginClick() {
        setIsLoading(true)
        mCompositeDisposable.add(
            dataManager.doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest("test3", "test4"))
                .doOnSuccess {
                    dataManager.updateUserInfo(
                        it.accessToken, it.userId, DataManager.LoggedInMode.LOGGED_IN_MODE_FB,
                        it.userName, it.userEmail, it.googleProfilePicUrl
                    )
                }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    setIsLoading(false)
                    mNavigator.get()?.openMainActivity()
                }, {
                    setIsLoading(false)
                    mNavigator.get()?.handleError(it)
                })
        )
    }

    fun onGoogleLoginClick() {
        setIsLoading(true)
        mCompositeDisposable.add(
            dataManager.doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest("test1", "test1"))
                .doOnSuccess {
                    dataManager.updateUserInfo(
                        it.accessToken, it.userId, DataManager.LoggedInMode.LOGGED_IN_MODE_GOOGLE,
                        it.userName, it.userEmail, it.googleProfilePicUrl
                    )
                }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    setIsLoading(false)
                    mNavigator.get()?.openMainActivity()
                }, {
                    setIsLoading(false)
                    mNavigator.get()?.handleError(it)
                })
        )
    }

    fun onServerLoginClick() {
        mNavigator.get()?.login()
    }
}