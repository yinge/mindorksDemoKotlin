package com.seven.mindorks.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * at 2019/12/3
 * at 10:54
 * summary:
 */
class BlogResponse {
    @Expose
    @SerializedName("data")
    var data: List<Blog>? = null
    @Expose
    @SerializedName("message")
    var message: String? = null
    @Expose
    @SerializedName("status_code")
    var statusCode: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other !is BlogResponse) {
            return false
        }

        val that = other as BlogResponse
        if (statusCode != that.statusCode) {
            return false
        }
        if (message != that.message) {
            return false
        }
        return data == that.data
    }

    override fun hashCode(): Int {
        var result = statusCode.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + data.hashCode()
        return result
    }

    class Blog {
        @Expose
        @SerializedName("author")
        var author: String = ""

        @Expose
        @SerializedName("blog_url")
        var blogUrl: String = ""

        @Expose
        @SerializedName("img_url")
        var coverImgUrl: String = ""

        @Expose
        @SerializedName("published_at")
        var date: String = ""

        @Expose
        @SerializedName("description")
        var description: String = ""

        @Expose
        @SerializedName("title")
        var title: String = ""

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }
            if (other !is Blog) {
                return false
            }

            val blog = other as Blog

            if (blogUrl != blog.blogUrl) {
                return false
            }
            if (coverImgUrl != blog.coverImgUrl) {
                return false
            }
            if (title != blog.title) {
                return false
            }
            if (description != blog.description) {
                return false
            }
            if (author != blog.author) {
                return false
            }
            return date == blog.date
        }

        override fun hashCode(): Int {
            var result = blogUrl.hashCode()
            result = 31 * result + coverImgUrl.hashCode()
            result = 31 * result + title.hashCode()
            result = 31 * result + description.hashCode()
            result = 31 * result + author.hashCode()
            result = 31 * result + date.hashCode()
            return result
        }
    }
}