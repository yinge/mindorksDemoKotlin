package com.seven.mindorks.ui.main.rating

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * at 2019/12/3
 * at 14:27
 * summary:
 */
@Module
abstract class RateUsDialogProvider {
    @ContributesAndroidInjector
    abstract fun provideRateUsDialogFactory(): RateUsDialog
}