package com.seven.mindorks.data.local.db

import com.seven.mindorks.data.model.db.Option
import com.seven.mindorks.data.model.db.Question
import com.seven.mindorks.data.model.db.User
import io.reactivex.Observable

/**
 * at 2019/12/2
 * at 15:50
 * summary:
 */

interface DbHelper {
    fun getAllQuestions(): Observable<List<Question>>

    fun getAllUsers(): Observable<List<User>>

    fun getOptionsForQuestionId(questionId: Long): Observable<List<Option>>

    fun insertUser(user: User): Observable<Boolean>

    fun isOptionEmpty(): Observable<Boolean>

    fun isQuestionEmpty(): Observable<Boolean>

    fun saveOption(option: Option): Observable<Boolean>

    fun saveOptionList(optionList: List<Option>): Observable<Boolean>

    fun saveQuestion(question: Question): Observable<Boolean>

    fun saveQuestionList(questionList: List<Question>): Observable<Boolean>
}