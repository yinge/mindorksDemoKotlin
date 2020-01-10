package com.seven.mindorks.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.internal.`$Gson$Types`
import com.google.gson.reflect.TypeToken
import com.seven.mindorks.data.local.db.DbHelper
import com.seven.mindorks.data.local.prefs.PreferencesHelper
import com.seven.mindorks.data.model.api.*
import com.seven.mindorks.data.model.db.Option
import com.seven.mindorks.data.model.db.Question
import com.seven.mindorks.data.model.db.User
import com.seven.mindorks.data.model.others.QuestionCardData
import com.seven.mindorks.data.remote.ApiHeader
import com.seven.mindorks.data.remote.ApiHelper
import com.seven.mindorks.utils.CommonUtils
import com.seven.mindorks.utils.SEED_DATABASE_OPTIONS
import com.seven.mindorks.utils.SEED_DATABASE_QUESTIONS
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import javax.inject.Inject
import javax.inject.Singleton

/**
 * at 2019/12/2
 * at 15:46
 * summary:
 */
@Singleton
class AppDataManager @Inject constructor(
    private val context: Context,
    private val dbHelper: DbHelper,
    private val preferencesHelper: PreferencesHelper,
    private val apiHelper: ApiHelper,
    private val gson: Gson
) : DataManager {
    override fun getQuestionCardData(): Observable<MutableList<QuestionCardData>> =
        dbHelper.getAllQuestions()
            .flatMap { questions -> Observable.fromIterable(questions) }
            .flatMap(Function { question ->
                Observable.zip(
                    dbHelper.getOptionsForQuestionId(question.id),
                    Observable.just(question),
                    BiFunction { options, question1 ->
                        mutableListOf(
                            QuestionCardData(
                                question1,
                                options
                            )
                        )
                    }
                )
            })


    override fun seedDatabaseOptions(): Observable<Boolean> {
        return dbHelper.isOptionEmpty()
            .concatMap {
                if (it) {
                    val type = object : TypeToken<List<Option>>() {}.type
                    val optionList = gson.fromJson<List<Option>>(
                        CommonUtils.loadJSONFromAsset(
                            context,
                            SEED_DATABASE_OPTIONS
                        ), type
                    )
                    saveOptionList(optionList)
                }
                Observable.just(false)
            }
    }

    override fun seedDatabaseQuestions(): Observable<Boolean> {
        return dbHelper.isQuestionEmpty()
            .concatMap {
                if (it) {
                    val type = `$Gson$Types`.newParameterizedTypeWithOwner(
                        null,
                        List::class.java,
                        Question::class.java
                    )
                    val questionList = gson.fromJson<List<Question>>(
                        CommonUtils.loadJSONFromAsset(
                            context,
                            SEED_DATABASE_QUESTIONS
                        ), type
                    )
                    saveQuestionList(questionList)
                }
                Observable.just(false)
            }
    }

    override fun setUserAsLoggedOut() {
        updateUserInfo(
            null,
            null,
            DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
            null,
            null,
            null
        )
    }

    override fun updateApiHeader(userId: Long?, accessToken: String?) {
        apiHelper.getApiHeader().protectedApiHeader.mUserId = userId
        apiHelper.getApiHeader().protectedApiHeader.mAccessToken = accessToken
    }

    override fun updateUserInfo(
        accessToken: String?,
        userId: Long?,
        loggedInMode: DataManager.LoggedInMode,
        userName: String?,
        email: String?,
        profilePicPath: String?
    ) {
        setAccessToken(accessToken)
        setCurrentUserId(userId)
        setCurrentUserLoggedInMode(loggedInMode)
        setCurrentUserName(userName)
        setCurrentUserEmail(email)
        setCurrentUserProfilePicUrl(profilePicPath)

        updateApiHeader(userId, accessToken)
    }

    override fun getAllQuestions(): Observable<List<Question>> = dbHelper.getAllQuestions()

    override fun getAllUsers(): Observable<List<User>> = dbHelper.getAllUsers()

    override fun getOptionsForQuestionId(questionId: Long): Observable<List<Option>> =
        dbHelper.getOptionsForQuestionId(questionId)

    override fun insertUser(user: User): Observable<Boolean> =
        dbHelper.insertUser(user)

    override fun isOptionEmpty(): Observable<Boolean> = dbHelper.isOptionEmpty()

    override fun isQuestionEmpty(): Observable<Boolean> = dbHelper.isQuestionEmpty()

    override fun saveOption(option: Option): Observable<Boolean> {
        return dbHelper.saveOption(option)
    }

    override fun saveOptionList(optionList: List<Option>): Observable<Boolean> {
        return dbHelper.saveOptionList(optionList)
    }

    override fun saveQuestion(question: Question): Observable<Boolean> =
        dbHelper.saveQuestion(question)

    override fun saveQuestionList(questionList: List<Question>): Observable<Boolean> =
        dbHelper.saveQuestionList(questionList)

    override fun getAccessToken(): String? = preferencesHelper.getAccessToken()

    override fun setAccessToken(accessToken: String?) {
        preferencesHelper.setAccessToken(accessToken)
        apiHelper.getApiHeader().protectedApiHeader.mAccessToken = accessToken
    }

    override fun getCurrentUserEmail(): String? = preferencesHelper.getCurrentUserEmail()

    override fun setCurrentUserEmail(email: String?) = preferencesHelper.setCurrentUserEmail(email)

    override fun getCurrentUserId(): Long? = preferencesHelper.getCurrentUserId()

    override fun setCurrentUserId(userId: Long?) = preferencesHelper.setCurrentUserId(userId)

    override fun getCurrentUserLoggedInMode(): Int = preferencesHelper.getCurrentUserLoggedInMode()

    override fun setCurrentUserLoggedInMode(mode: DataManager.LoggedInMode) =
        preferencesHelper.setCurrentUserLoggedInMode(mode)

    override fun getCurrentUserName(): String? = preferencesHelper.getCurrentUserName()

    override fun setCurrentUserName(userName: String?) =
        preferencesHelper.setCurrentUserName(userName)

    override fun getCurrentUserProfilePicUrl(): String? =
        preferencesHelper.getCurrentUserProfilePicUrl()

    override fun setCurrentUserProfilePicUrl(profilePicUrl: String?) =
        preferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl)

    override fun doFacebookLoginApiCall(request: LoginRequest.FacebookLoginRequest): Single<LoginResponse> =
        apiHelper.doFacebookLoginApiCall(request)

    override fun doGoogleLoginApiCall(request: LoginRequest.GoogleLoginRequest): Single<LoginResponse> =
        apiHelper.doGoogleLoginApiCall(request)

    override fun doLogoutApiCall(): Single<LogoutResponse> =
        apiHelper.doLogoutApiCall()

    override fun doServerLoginApiCall(request: LoginRequest.ServerLoginRequest): Single<LoginResponse> =
        apiHelper.doServerLoginApiCall(request)

    override fun getApiHeader(): ApiHeader =
        apiHelper.getApiHeader()

    override fun getBlogApiCall(): Single<BlogResponse> =
        apiHelper.getBlogApiCall()

    override fun getOpenSourceApiCall(): Single<OpenSourceResponse> =
        apiHelper.getOpenSourceApiCall()
}