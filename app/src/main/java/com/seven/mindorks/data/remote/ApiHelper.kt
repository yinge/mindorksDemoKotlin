package com.seven.mindorks.data.remote

import com.seven.mindorks.data.model.api.*
import io.reactivex.Single

/**
 * at 2019/12/2
 * at 15:10
 * summary:
 */
interface ApiHelper {
    fun doFacebookLoginApiCall(request: LoginRequest.FacebookLoginRequest): Single<LoginResponse>

    fun doGoogleLoginApiCall(request: LoginRequest.GoogleLoginRequest): Single<LoginResponse>

    fun doLogoutApiCall(): Single<LogoutResponse>

    fun doServerLoginApiCall(request: LoginRequest.ServerLoginRequest): Single<LoginResponse>

    fun getApiHeader(): ApiHeader

    fun getBlogApiCall(): Single<BlogResponse>

    fun getOpenSourceApiCall(): Single<OpenSourceResponse>
}