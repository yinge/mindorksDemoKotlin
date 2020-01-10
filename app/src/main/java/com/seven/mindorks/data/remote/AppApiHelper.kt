package com.seven.mindorks.data.remote

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.seven.mindorks.data.model.api.*
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * at 2019/12/2
 * at 14:57
 * summary:
 */
@Singleton
class AppApiHelper @Inject constructor(private val apiHeader: ApiHeader) : ApiHelper {
    override fun doFacebookLoginApiCall(request: LoginRequest.FacebookLoginRequest): Single<LoginResponse> =
        Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_FACEBOOK_LOGIN)
            .addHeaders(apiHeader.publicApiHeader)
            .addBodyParameter(request)
            .build()
            .getObjectSingle(LoginResponse::class.java)


    override fun doGoogleLoginApiCall(request: LoginRequest.GoogleLoginRequest): Single<LoginResponse> =
        Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GOOGLE_LOGIN)
            .addHeaders(apiHeader.publicApiHeader)
            .addBodyParameter(request)
            .build()
            .getObjectSingle(LoginResponse::class.java)

    override fun doLogoutApiCall(): Single<LogoutResponse> =
        Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGOUT)
            .addHeaders(apiHeader.protectedApiHeader)
            .build()
            .getObjectSingle(LogoutResponse::class.java)

    override fun doServerLoginApiCall(request: LoginRequest.ServerLoginRequest): Single<LoginResponse> =
        Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
            .addHeaders(apiHeader.publicApiHeader)
            .addBodyParameter(request)
            .build()
            .getObjectSingle(LoginResponse::class.java)

    override fun getApiHeader(): ApiHeader = apiHeader

    override fun getBlogApiCall(): Single<BlogResponse> =
        Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BLOG)
            .addHeaders(apiHeader.protectedApiHeader)
            .build()
            .getObjectSingle(BlogResponse::class.java)

    override fun getOpenSourceApiCall(): Single<OpenSourceResponse> =
        Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_OPEN_SOURCE)
            .addHeaders(apiHeader.protectedApiHeader)
            .build()
            .getObjectSingle(OpenSourceResponse::class.java)
}