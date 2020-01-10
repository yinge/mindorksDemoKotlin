package com.seven.mindorks.data.local.db

import com.seven.mindorks.data.model.db.Option
import com.seven.mindorks.data.model.db.Question
import com.seven.mindorks.data.model.db.User
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * at 2019/12/2
 * at 15:50
 * summary:
 */
@Singleton
class AppDbHelper @Inject constructor(private val appDatabase: AppDatabase) : DbHelper {

    override fun getAllQuestions(): Observable<List<Question>> =
        appDatabase.questionDao().loadAll().toObservable()

    override fun getAllUsers(): Observable<List<User>> =
        Observable.fromCallable { appDatabase.userDao().loadAll() }

    override fun getOptionsForQuestionId(questionId: Long): Observable<List<Option>> =
        appDatabase.optionDao().loadAllByQuestionId(questionId).toObservable()


    override fun insertUser(user: User): Observable<Boolean> =
        Observable.fromCallable {
            appDatabase.userDao().insert(user)
            true
        }


    override fun isOptionEmpty(): Observable<Boolean> =
        appDatabase.optionDao().loadAll()
            .flatMapObservable { options -> Observable.just(options.isEmpty()) }

    override fun isQuestionEmpty(): Observable<Boolean> =
        appDatabase.questionDao().loadAll()
            .flatMapObservable { question -> Observable.just(question.isEmpty()) }

    override fun saveOption(option: Option): Observable<Boolean> =
        Observable.fromCallable {
            appDatabase.optionDao().insert(option)
            true
        }

    override fun saveOptionList(optionList: List<Option>): Observable<Boolean> =
        Observable.fromCallable {
            appDatabase.optionDao().insertAll(optionList)
            true
        }

    override fun saveQuestion(question: Question): Observable<Boolean> =
        Observable.fromCallable {
            appDatabase.questionDao().insert(question)
            true
        }

    override fun saveQuestionList(questionList: List<Question>): Observable<Boolean> =
        Observable.fromCallable {
            appDatabase.questionDao().insertAll(questionList)
            true
        }

}