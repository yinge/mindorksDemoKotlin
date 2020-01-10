package com.seven.mindorks.data.local.prefs

import com.seven.mindorks.data.DataManager

/**
 * at 2019/12/2
 * at 15:59
 * summary:
 */
interface PreferencesHelper {
    fun getAccessToken(): String?

    fun setAccessToken(accessToken: String?)

    fun getCurrentUserEmail(): String?

    fun setCurrentUserEmail(email: String?)

    fun getCurrentUserId(): Long?

    fun setCurrentUserId(userId: Long?)

    fun getCurrentUserLoggedInMode(): Int

    fun setCurrentUserLoggedInMode(mode: DataManager.LoggedInMode)

    fun getCurrentUserName(): String?

    fun setCurrentUserName(userName: String?)

    fun getCurrentUserProfilePicUrl(): String?

    fun setCurrentUserProfilePicUrl(profilePicUrl: String?)
}