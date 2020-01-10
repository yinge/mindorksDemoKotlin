package com.seven.mindorks.ui.main

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.seven.mindorks.data.DataManager
import com.seven.mindorks.data.model.others.QuestionCardData
import com.seven.mindorks.ui.base.BaseViewModel
import com.seven.mindorks.utils.rx.SchedulerProvider

/**
 * at 2019/12/3
 * at 14:26
 * summary:
 */
class MainViewModel constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {

    var action = NO_ACTION

    val questionDataList: ObservableList<QuestionCardData> =
        ObservableArrayList<QuestionCardData>()

    val appVersion = ObservableField<String>()


    val questionCardData: MutableLiveData<MutableList<QuestionCardData>> = MutableLiveData()

    val userEmail = ObservableField<String>()

    val userName = ObservableField<String>()

    init {
        loadQuestionCards()
    }

    fun loadQuestionCards() {
        mCompositeDisposable.add(
            dataManager.getQuestionCardData()
                .doOnNext { list -> Log.d(TAG, "loadQuestionCards: ${list.size}") }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    it?.let {
                        Log.d(TAG, "loadQuestionCards: ${it.size}")
                        action = ACTION_ADD_ALL
                        questionCardData.value = it
                    }
                }, {
                    Log.d(TAG, "loadQuestionCards: $it")
                })
        )
    }

    fun logout() {
        setIsLoading(true)
        mCompositeDisposable.add(
            dataManager.doLogoutApiCall()
                .doOnSuccess { dataManager.setUserAsLoggedOut() }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    setIsLoading(false)
                    mNavigator.get()?.openLoginActivity()
                }, {
                    setIsLoading(false)
                    mNavigator.get()?.handleError(it)
                })
        )
    }

    fun onNavMenuCreated() {
        val currentUserName = dataManager.getCurrentUserName() ?: ""
        if (currentUserName.isNotEmpty()) {
            userName.set(currentUserName)
        }

        val currentUserEmail = dataManager.getCurrentUserEmail() ?: ""
        if (currentUserEmail.isNotEmpty()) {
            userEmail.set(currentUserEmail)
        }

        val profilePicUrl = dataManager.getCurrentUserProfilePicUrl() ?: ""
        if (profilePicUrl.isNotEmpty()) {
            userProfilePicUrl.set(profilePicUrl)
        }
    }

    fun setQuestionDataList(questionCardDatas: List<QuestionCardData>?) {
        questionCardDatas?.let {
            action = ACTION_ADD_ALL
            questionDataList.clear()
            questionDataList.addAll(questionCardDatas)
        }
    }

    fun removeQuestionCard() {
        action = ACTION_DELETE_SINGLE
        questionCardData.value?.removeAt(0)
    }

    fun updateAppVersion(version: String) {
        appVersion.set(version)
    }

    fun getQuestionCardData(): LiveData<MutableList<QuestionCardData>> {
        return questionCardData
    }

    companion object {
        const val TAG = "MainViewModel"
        const val NO_ACTION = -1
        const val ACTION_ADD_ALL = 0
        const val ACTION_DELETE_SINGLE = 1

        private val userProfilePicUrl = ObservableField<String>()


    }
}