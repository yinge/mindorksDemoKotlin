package com.seven.mindorks.ui.feed.opensource

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.seven.mindorks.BR
import com.seven.mindorks.R
import com.seven.mindorks.ViewModelProviderFactory
import com.seven.mindorks.databinding.FragmentOpenSourceBinding
import com.seven.mindorks.ui.base.BaseFragment
import javax.inject.Inject

/**
 * at 2019/12/3
 * at 11:47
 * summary:
 */
class OpenSourceFragment : BaseFragment<FragmentOpenSourceBinding, OpenSourceViewModel>(),
    OpenSourceNavigator, OpenSourceAdapter.OpenSourceAdapterListener {

    @Inject
    lateinit var mLayoutManager: LinearLayoutManager
    @Inject
    lateinit var mOpenSourceAdapter: OpenSourceAdapter
    @Inject
    lateinit var factory: ViewModelProviderFactory
    private lateinit var mOpenSourceViewModel: OpenSourceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mOpenSourceViewModel.setNavigator(this)
        mOpenSourceAdapter.setListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        mViewDataBinding.openSourceRecyclerView.layoutManager = mLayoutManager
        mViewDataBinding.openSourceRecyclerView.itemAnimator = DefaultItemAnimator()
        mViewDataBinding.openSourceRecyclerView.adapter = mOpenSourceAdapter
    }

    override fun handleError(throwable: Throwable) {

    }

    override fun onRetryClick() {
        mOpenSourceViewModel.fetchRepos()
    }

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_open_source

    override fun getViewModel(): OpenSourceViewModel {
        mOpenSourceViewModel =
            ViewModelProviders.of(this, factory).get(OpenSourceViewModel::class.java)
        return mOpenSourceViewModel
    }

    companion object {
        fun newInstance(): OpenSourceFragment = OpenSourceFragment()

    }
}