package com.seven.mindorks.data

import com.seven.mindorks.data.local.db.DbHelper
import com.seven.mindorks.data.local.prefs.PreferencesHelper
import com.seven.mindorks.data.model.others.QuestionCardData
import com.seven.mindorks.data.remote.ApiHelper
import io.reactivex.Observable

/**
 * at 2019/12/2
 * at 15:45
 * summary:
 */
interface DataManager : DbHelper, PreferencesHelper, ApiHelper {

    fun getQuestionCardData(): Observable<MutableList<QuestionCardData>>

    fun seedDatabaseOptions(): Observable<Boolean>

    fun seedDatabaseQuestions(): Observable<Boolean>

    fun setUserAsLoggedOut()

    fun updateApiHeader(userId: Long?, accessToken: String?)

    fun updateUserInfo(
        accessToken: String?,
        userId: Long?,
        loggedInMode: LoggedInMode,
        userName: String?,
        email: String?,
        profilePicPath: String?
    )

    enum class LoggedInMode(var mType: Int) {
        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);
    }
}