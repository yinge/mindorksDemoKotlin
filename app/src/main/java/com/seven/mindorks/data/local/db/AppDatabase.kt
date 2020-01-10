package com.seven.mindorks.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seven.mindorks.data.local.db.dao.OptionDao
import com.seven.mindorks.data.local.db.dao.QuestionDao
import com.seven.mindorks.data.local.db.dao.UserDao
import com.seven.mindorks.data.model.db.Option
import com.seven.mindorks.data.model.db.Question
import com.seven.mindorks.data.model.db.User

/**
 * at 2019/12/2
 * at 15:24
 * summary:
 */
@Database(entities = [Option::class, Question::class, User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun optionDao(): OptionDao

    abstract fun questionDao(): QuestionDao

    abstract fun userDao(): UserDao
}