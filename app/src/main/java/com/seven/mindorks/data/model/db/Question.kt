package com.seven.mindorks.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * at 2019/12/3
 * at 15:03
 * summary:
 */
@Entity(tableName = "questions")
data class Question(
    @Expose
    @PrimaryKey
    var id: Long,

    @Expose
    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    var createdAt: String,

    @Expose
    @SerializedName("question_img_url")
    @ColumnInfo(name = "question_img_url")
    var imgUrl: String,

    @Expose
    @SerializedName("question_text")
    @ColumnInfo(name = "question_text")
    var questionText: String,

    @Expose
    @SerializedName("updated_at")
    @ColumnInfo(name = "updated_at")
    var updatedAt: String
)