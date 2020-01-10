package com.seven.mindorks.ui.about

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.seven.mindorks.BR
import com.seven.mindorks.R
import com.seven.mindorks.ViewModelProviderFactory
import com.seven.mindorks.databinding.FragmentAboutBinding
import com.seven.mindorks.ui.base.BaseFragment
import javax.inject.Inject

/**
 * at 2019/12/3
 * at 14:22
 * summary:
 */
class AboutFragment : BaseFragment<FragmentAboutBinding, AboutViewModel>(), AboutNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    lateinit var aboutViewModel: AboutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aboutViewModel.setNavigator(this)
    }

    override fun goBack() {
        mActivity?.onFragmentDetached(TAG)
    }

    companion object {
        const val TAG = "AboutFragment"
        fun newInstance(): AboutFragment = AboutFragment()
    }

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_about

    override fun getViewModel(): AboutViewModel {
        aboutViewModel = ViewModelProviders.of(this, factory).get(AboutViewModel::class.java)
        return aboutViewModel
    }
}