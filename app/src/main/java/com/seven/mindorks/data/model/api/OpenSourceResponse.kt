package com.seven.mindorks.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * at 2019/12/3
 * at 18:44
 * summary:
 */
data class OpenSourceResponse(
    @Expose
    @SerializedName("data")
    var data: List<Repo>,

    @Expose
    @SerializedName("message")
    private val message: String,

    @Expose
    @SerializedName("status_code")
    private val statusCode: String
) {
    data class Repo(
        @Expose @SerializedName("img_url")
        var coverImgUrl: String,

        @Expose
        @SerializedName("description")
        val description: String,

        @Expose
        @SerializedName("project_url")
        val projectUrl: String,

        @Expose
        @SerializedName("title")
        val title: String
    )
}