package com.seven.mindorks.data.remote

import com.seven.mindorks.BuildConfig

/**
 * at 2019/12/4
 * at 9:46
 * summary:
 */
class ApiEndPoint {
    companion object {
        const val ENDPOINT_BLOG: String =
            BuildConfig.BASE_URL + "/5926ce9d11000096006ccb30"

        const val ENDPOINT_FACEBOOK_LOGIN: String =
            BuildConfig.BASE_URL + "/588d15d3100000ae072d2944"

        const val ENDPOINT_GOOGLE_LOGIN: String =
            BuildConfig.BASE_URL + "/588d14f4100000a9072d2943"

        const val ENDPOINT_LOGOUT: String =
            BuildConfig.BASE_URL + "/588d161c100000a9072d2946"

        const val ENDPOINT_OPEN_SOURCE: String =
            BuildConfig.BASE_URL + "/5926c34212000035026871cd"

        const val ENDPOINT_SERVER_LOGIN: String =
            BuildConfig.BASE_URL + "/588d15f5100000a8072d2945"
    }
}