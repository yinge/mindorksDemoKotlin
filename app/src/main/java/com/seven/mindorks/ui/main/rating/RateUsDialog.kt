package com.seven.mindorks.ui.main.rating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.seven.mindorks.R
import com.seven.mindorks.ViewModelProviderFactory
import com.seven.mindorks.databinding.DialogRateUsBinding
import com.seven.mindorks.ui.base.BaseDialog
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * at 2019/12/3
 * at 14:28
 * summary:
 */
class RateUsDialog : BaseDialog(), RateUsCallback {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var rateUsViewModel: RateUsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<DialogRateUsBinding>(
            layoutInflater,
            R.layout.dialog_rate_us,
            container,
            false
        )
        AndroidSupportInjection.inject(this)
        rateUsViewModel = ViewModelProviders.of(this, factory).get(RateUsViewModel::class.java)
        binding.viewModel = rateUsViewModel
        rateUsViewModel.setNavigator(this)
        return binding.root
    }

    fun show(fragmentManager: FragmentManager) {
        show(fragmentManager, TAG)
    }

    override fun dismissDialog() {
        dismissDialog(TAG)
    }

    companion object {
        const val TAG = "RateUsDialog"
        fun newInstance(): RateUsDialog {
            return RateUsDialog().apply {
                arguments = Bundle()
            }
        }

    }
}