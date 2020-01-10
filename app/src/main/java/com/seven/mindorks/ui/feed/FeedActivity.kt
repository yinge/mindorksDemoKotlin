package com.seven.mindorks.ui.feed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.app.NavUtils
import androidx.core.app.TaskStackBuilder
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.seven.mindorks.BR
import com.seven.mindorks.R
import com.seven.mindorks.ViewModelProviderFactory
import com.seven.mindorks.databinding.ActivityFeedBinding
import com.seven.mindorks.ui.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * at 2019/12/2
 * at 17:47
 * summary:
 */
class FeedActivity : BaseActivity<ActivityFeedBinding, FeedViewModel>(),
    HasSupportFragmentInjector {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var mPagerAdapter: FeedPagerAdapter

    lateinit var feedViewModel: FeedViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_feed

    override fun getViewModel(): FeedViewModel {
        feedViewModel = factory.create(FeedViewModel::class.java)
        return feedViewModel
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        fragmentDispatchingAndroidInjector

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val upIntent = NavUtils.getParentActivityIntent(this)
            upIntent?.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            if (NavUtils.shouldUpRecreateTask(this, upIntent!!)) {
                TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent)
                    .startActivities()
            } else {
                NavUtils.navigateUpTo(this, upIntent)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
    }

    private fun setUp() {
        setSupportActionBar(getViewDataBinding().toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
        mPagerAdapter.mTabCount = 2
        getViewDataBinding().feedViewPager.adapter = mPagerAdapter

        getViewDataBinding().tabLayout.addTab(
            getViewDataBinding().tabLayout.newTab().setText(
                getString(R.string.blog)
            )
        )
        getViewDataBinding().tabLayout.addTab(
            getViewDataBinding().tabLayout.newTab().setText(
                getString(R.string.open_source)
            )
        )

        getViewDataBinding().feedViewPager.offscreenPageLimit =
            getViewDataBinding().tabLayout.tabCount

        getViewDataBinding().feedViewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                getViewDataBinding().tabLayout
            )
        )

        getViewDataBinding().tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                getViewDataBinding().feedViewPager.currentItem = p0!!.position
            }

        })
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, FeedActivity::class.java)
    }
}