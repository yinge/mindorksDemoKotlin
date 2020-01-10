package com.seven.mindorks.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.seven.mindorks.BuildConfig
import com.seven.mindorks.R
import com.seven.mindorks.data.AppDataManager
import com.seven.mindorks.data.DataManager
import com.seven.mindorks.data.local.db.AppDatabase
import com.seven.mindorks.data.local.db.AppDbHelper
import com.seven.mindorks.data.local.db.DbHelper
import com.seven.mindorks.data.local.prefs.AppPreferencesHelper
import com.seven.mindorks.data.local.prefs.PreferencesHelper
import com.seven.mindorks.data.remote.ApiHeader
import com.seven.mindorks.data.remote.ApiHelper
import com.seven.mindorks.data.remote.AppApiHelper
import com.seven.mindorks.di.ApiInfo
import com.seven.mindorks.di.DatabaseInfo
import com.seven.mindorks.di.PreferenceInfo
import com.seven.mindorks.utils.DB_NAME
import com.seven.mindorks.utils.PREF_NAME
import com.seven.mindorks.utils.rx.AppSchedulerProvider
import com.seven.mindorks.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Singleton

/**
 * at 2019/12/2
 * at 15:03
 * summary:
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

    @Provides
    @ApiInfo
    fun provideApiKey(): String = BuildConfig.API_KEY

    @Provides
    @Singleton
    fun provideAppDatabase(@DatabaseInfo dbName: String, context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager = appDataManager

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String = DB_NAME

    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper = appDbHelper

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    @PreferenceInfo
    fun providePreferenceName(): String = PREF_NAME

    @Provides
    @Singleton
    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper =
        appPreferencesHelper


    @Provides
    @Singleton
    fun provideProtectedApiHeader(@ApiInfo apiKey: String, preferencesHelper: PreferencesHelper): ApiHeader.ProtectedApiHeader {
        return ApiHeader.ProtectedApiHeader(
            apiKey,
            preferencesHelper.getAccessToken(),
            preferencesHelper.getCurrentUserId()
        )
    }


    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

}