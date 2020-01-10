package com.seven.mindorks.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.seven.mindorks.ui.login.LoginActivity
import com.seven.mindorks.utils.CommonUtils
import com.seven.mindorks.utils.NetworkUtils
import dagger.android.AndroidInjection
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * at 2019/12/2
 * at 16:44
 * summary:
 */
abstract class BaseActivity<DB : ViewDataBinding, VM : BaseViewModel<*>>
    : AppCompatActivity(),
    BaseFragment.Callback {

    private lateinit var mProgressDialog: ProgressDialog
    private var mViewDataBinding: DB? = null
    private var mViewModel: VM? = null

    abstract fun getBindingVariable(): Int

    fun getViewDataBinding(): DB = mViewDataBinding!!

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): VM

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String?) {

    }

//    override fun attachBaseContext(newBase: Context?) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding!!.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding!!.executePendingBindings()
    }

    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    fun hideKeyboard() {
        val v = currentFocus
        v?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    fun hideLoading() {
        mProgressDialog?.let {
            if (mProgressDialog.isShowing) {
                it.cancel()
            }
        }
    }

    fun isNetworkConnected(): Boolean = NetworkUtils.isNetworkConnected(applicationContext)

    fun openActivityOnTokenExpire() {
        startActivity(LoginActivity.newIntent(this))
        finish()
    }

    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    fun showLoading() {
        hideLoading()
        mProgressDialog = CommonUtils.showLoadingDialog(this)
    }

}