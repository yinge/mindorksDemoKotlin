package com.seven.mindorks.di.component

import android.app.Application
import com.seven.mindorks.MvvmApp
import com.seven.mindorks.di.builder.ActivityBuilder
import com.seven.mindorks.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * at 2019/12/2
 * at 14:19
 * summary:
 */
@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class]
)
interface AppComponent {

    fun inject(app: MvvmApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}