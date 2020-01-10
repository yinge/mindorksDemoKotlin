package com.seven.mindorks.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.seven.mindorks.data.DataManager
import com.seven.mindorks.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * at 2019/12/2
 * at 16:51
 * summary:
 */
abstract class BaseViewModel<N> constructor(
    val dataManager: DataManager,
    val schedulerProvider: SchedulerProvider
) : ViewModel() {
    lateinit var mNavigator: WeakReference<N>

    override fun onCleared() {
        mCompositeDisposable.clear()
        super.onCleared()
    }

    fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }

    fun getIsLoading(): ObservableBoolean {
        return mIsLoading
    }

    fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }

    companion object {
        private val mIsLoading = ObservableBoolean()
        val mCompositeDisposable = CompositeDisposable()
    }
}