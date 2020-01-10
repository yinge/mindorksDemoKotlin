package com.seven.mindorks.ui.feed.blogs

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.seven.mindorks.BR
import com.seven.mindorks.R
import com.seven.mindorks.ViewModelProviderFactory
import com.seven.mindorks.data.model.api.BlogResponse
import com.seven.mindorks.databinding.FragmentBlogBinding
import com.seven.mindorks.ui.base.BaseFragment
import javax.inject.Inject

/**
 * at 2019/12/3
 * at 10:44
 * summary:
 */
class BlogFragment : BaseFragment<FragmentBlogBinding, BlogViewModel>(), BlogNavigator,
    BlogAdapter.BlogAdapterListener {

    @Inject
    lateinit var mBlogAdapter: BlogAdapter
    @Inject
    lateinit var mLayoutManager: LinearLayoutManager
    @Inject
    lateinit var factory: ViewModelProviderFactory
    private lateinit var mBlogViewModel: BlogViewModel

    companion object {
        fun newInstance(): BlogFragment = BlogFragment()
    }

    override fun handleError(throwable: Throwable) {

    }

    override fun updateBlog(blogList: List<BlogResponse.Blog>) {
        mBlogAdapter.addItems(blogList)
    }

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_blog

    override fun getViewModel(): BlogViewModel {
        mBlogViewModel = factory.create(BlogViewModel::class.java)
        return mBlogViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBlogViewModel.setNavigator(this)
        mBlogAdapter.setListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        mViewDataBinding.blogRecyclerView.layoutManager = mLayoutManager
        mViewDataBinding.blogRecyclerView.itemAnimator = DefaultItemAnimator()
        mViewDataBinding.blogRecyclerView.adapter = mBlogAdapter
    }

    override fun onRetryClick() {
        mBlogViewModel.fetchBlogs()
    }
}