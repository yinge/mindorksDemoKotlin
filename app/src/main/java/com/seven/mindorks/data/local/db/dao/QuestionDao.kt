package com.seven.mindorks.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seven.mindorks.data.model.db.Question
import io.reactivex.Single

/**
 * at 2019/12/3
 * at 15:48
 * summary:
 */
@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(question: Question)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(questions: List<Question>)

    @Query("SELECT * FROM questions")
    fun loadAll(): Single<List<Question>>
}