package com.seven.mindorks.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * at 2019/12/3
 * at 18:39
 * summary:
 */
data class LogoutResponse(
    @Expose @SerializedName("message")
    private var message: String,

    @Expose
    @SerializedName("status_code")
    private val statusCode: String
)