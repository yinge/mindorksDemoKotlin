package com.seven.mindorks.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import com.seven.mindorks.data.DataManager
import com.seven.mindorks.di.PreferenceInfo
import com.seven.mindorks.utils.NULL_INDEX
import javax.inject.Inject

/**
 * at 2019/12/2
 * at 15:59
 * summary:
 */
class AppPreferencesHelper @Inject constructor(
    context: Context,
    @PreferenceInfo
    prefFileName: String
) : PreferencesHelper {

    private val mPrefs: SharedPreferences =
        context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun getAccessToken(): String? =
        mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)

    override fun setAccessToken(accessToken: String?) =
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()


    override fun getCurrentUserEmail(): String? =
        mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null)

    override fun setCurrentUserEmail(email: String?) =
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply()


    override fun getCurrentUserId(): Long? {
        val userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, NULL_INDEX)
        return if (userId == NULL_INDEX) null else userId
    }

    override fun setCurrentUserId(userId: Long?) {
        val id = userId ?: NULL_INDEX
        mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply()
    }

    override fun getCurrentUserLoggedInMode(): Int =
        mPrefs.getInt(
            PREF_KEY_USER_LOGGED_IN_MODE,
            DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.mType
        )

    override fun setCurrentUserLoggedInMode(mode: DataManager.LoggedInMode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.mType).apply()
    }

    override fun getCurrentUserName(): String? =
        mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null)

    override fun setCurrentUserName(userName: String?) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply()
    }

    override fun getCurrentUserProfilePicUrl(): String? =
        mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null)

    override fun setCurrentUserProfilePicUrl(profilePicUrl: String?) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply()
    }

    companion object {
        private const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"

        private const val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"

        private const val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"

        private const val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"

        private const val PREF_KEY_CURRENT_USER_PROFILE_PIC_URL =
            "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL"

        private const val PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE"
    }
}