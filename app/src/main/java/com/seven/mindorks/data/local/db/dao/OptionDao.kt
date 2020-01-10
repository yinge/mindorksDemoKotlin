package com.seven.mindorks.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seven.mindorks.data.model.db.Option
import io.reactivex.Single

/**
 * at 2019/12/3
 * at 15:47
 * summary:
 */
@Dao
interface OptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(option: Option)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(options: List<Option>)

    @Query("SELECT * FROM options")
    fun loadAll(): Single<List<Option>>

    @Query("SELECT * FROM options WHERE question_id = :questionId")
    fun loadAllByQuestionId(questionId: Long): Single<List<Option>>
}