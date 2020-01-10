package com.seven.mindorks.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

/**
 * at 2019/12/2
 * at 16:45
 * summary:
 */
abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel<*>> : Fragment() {

    protected var mActivity: BaseActivity<*, *>? = null
    private lateinit var mRootView: View
    protected lateinit var mViewDataBinding: DB
    private lateinit var mViewModel: VM

    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): VM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            mActivity = context
            context.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mRootView = mViewDataBinding.root
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.executePendingBindings()
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    private fun performDependencyInjection() = AndroidSupportInjection.inject(this)

    fun hideKeyboard() {
        mActivity?.hideKeyboard()
    }

    fun isNetworkConnected(): Boolean = mActivity != null && mActivity!!.isNetworkConnected()

    fun openActivityOnTokenExpire() = mActivity?.openActivityOnTokenExpire()

    interface Callback {
        fun onFragmentAttached()

        fun onFragmentDetached(tag: String?)
    }
}