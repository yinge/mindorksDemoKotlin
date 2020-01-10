package com.seven.mindorks.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * at 2019/12/3
 * at 15:30
 * summary:
 */
@Entity(tableName = "users")
data class User(
    @ColumnInfo(name = "created_at")
    var createdAt: String,

    @PrimaryKey
    var id: Long,

    var name: String,

    @ColumnInfo(name = "updated_at")
    var updatedAt: String
)