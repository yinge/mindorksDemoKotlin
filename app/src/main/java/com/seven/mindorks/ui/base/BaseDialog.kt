package com.seven.mindorks.ui.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

/**
 * at 2019/12/5
 * at 9:46
 * summary:
 */
abstract class BaseDialog : DialogFragment() {

    private var mActivity: BaseActivity<*, *>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            this.mActivity = context
            context.onFragmentAttached()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = RelativeLayout(activity).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        val dialog = Dialog(context!!).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(root)
        }
        dialog.window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        val transaction = manager.beginTransaction()
        val prevFragment = manager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }

    fun dismissDialog(tag: String?) {
        dismiss()
        mActivity?.onFragmentDetached(tag)
    }

    fun hideKeyboard() {
        mActivity?.hideKeyboard()
    }

    fun hideLoading() {
        mActivity?.hideLoading()
    }

    fun isNetworkConnected(): Boolean {
        mActivity?.isNetworkConnected()
        return false
    }

    fun openActivityOnTokenExpire() {
        mActivity?.openActivityOnTokenExpire()
    }

    fun showLoading() {
        mActivity?.showLoading()
    }
}