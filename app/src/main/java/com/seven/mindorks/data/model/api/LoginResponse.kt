package com.seven.mindorks.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * at 2019/12/3
 * at 18:35
 * summary:
 */
data class LoginResponse(
    @Expose
    @SerializedName("access_token")
    var accessToken: String,

    @Expose
    @SerializedName("fb_profile_pic_url")
    var fbProfilePicUrl: String,

    @Expose
    @SerializedName("google_profile_pic_url")
    var googleProfilePicUrl: String,

    @Expose
    @SerializedName("message")
    var message: String,

    @Expose
    @SerializedName("server_profile_pic_url")
    var serverProfilePicUrl: String,

    @Expose
    @SerializedName("status_code")
    var statusCode: String,

    @Expose
    @SerializedName("email")
    var userEmail: String,

    @Expose
    @SerializedName("user_id")
    var userId: Long,

    @Expose
    @SerializedName("user_name")
    var userName: String
)