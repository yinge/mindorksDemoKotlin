package com.seven.mindorks.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.seven.mindorks.di.ApiInfo
import javax.inject.Inject
import javax.inject.Singleton

/**
 * at 2019/12/2
 * at 16:13
 * summary:
 */
@Singleton
class ApiHeader @Inject constructor(
    val publicApiHeader: PublicApiHeader,
    val protectedApiHeader: ProtectedApiHeader
) {
    data class ProtectedApiHeader(

        @Expose
        @SerializedName("api_key")
        var mApiKey: String,

        @Expose
        @SerializedName("access_token")
        var mAccessToken: String?,

        @Expose
        @SerializedName("user_id")
        var mUserId: Long?
    )

    class PublicApiHeader @Inject constructor(@ApiInfo apiKey: String) {

        @Expose
        @SerializedName("api_key")
        private val mApiKey: String = apiKey
    }
}