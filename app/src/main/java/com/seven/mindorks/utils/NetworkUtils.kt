package com.seven.mindorks.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * at 2019/12/2
 * at 18:17
 * summary:
 */
class NetworkUtils {
    companion object {

        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            cm?.let {
                val activeNetwork = it.activeNetworkInfo
                return activeNetwork != null && activeNetwork.isConnectedOrConnecting()
            }
            return false
        }
    }
}