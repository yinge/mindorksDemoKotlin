package com.seven.mindorks.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * at 2019/12/3
 * at 18:05
 * summary:
 */
class LoginRequest {
    data class FacebookLoginRequest(
        @Expose @SerializedName("fb_access_token")
        private var fbAccessToken: String,

        @Expose
        @SerializedName("fb_user_id")
        private val fbUserId: String
    )

    data class GoogleLoginRequest(
        @Expose @SerializedName("google_user_id")
        private var googleUserId: String,

        @Expose
        @SerializedName("google_id_token")
        private val idToken: String
    )

    data class ServerLoginRequest(
        @Expose @SerializedName("email")
        private var email: String,

        @Expose
        @SerializedName("password")
        private val password: String
    )
}