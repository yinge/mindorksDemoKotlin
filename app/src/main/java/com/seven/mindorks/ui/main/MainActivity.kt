package com.seven.mindorks.ui.main

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.seven.mindorks.BR
import com.seven.mindorks.BuildConfig
import com.seven.mindorks.R
import com.seven.mindorks.ViewModelProviderFactory
import com.seven.mindorks.data.model.others.QuestionCardData
import com.seven.mindorks.databinding.ActivityMainBinding
import com.seven.mindorks.databinding.NavHeaderMainBinding
import com.seven.mindorks.ui.about.AboutFragment
import com.seven.mindorks.ui.base.BaseActivity
import com.seven.mindorks.ui.feed.FeedActivity
import com.seven.mindorks.ui.login.LoginActivity
import com.seven.mindorks.ui.main.rating.RateUsDialog
import com.seven.mindorks.utils.ScreenUtils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator,
    HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var mCardsContainerView: SwipePlaceHolderView
    private lateinit var mDrawer: DrawerLayout
    private lateinit var mMainViewModel: MainViewModel
    private lateinit var mNavigationView: NavigationView
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainViewModel.setNavigator(this)
        setUp()
    }

    override fun onResume() {
        super.onResume()
        mDrawer?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): MainViewModel {
//        mMainViewModel = factory.create(MainViewModel::class.java)
        mMainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        return mMainViewModel
    }

    override fun handleError(throwable: Throwable) {

    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(AboutFragment.TAG)
        if (fragment == null) {
            super.onBackPressed()
        } else {
            onFragmentDetached(AboutFragment.TAG)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val drawable = item.icon
        if (drawable is Animatable) {
            drawable.start()
        }
        return when (item.itemId) {
            R.id.action_cut -> true
            R.id.action_copy -> true
            R.id.action_share -> true
            R.id.action_delete -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onFragmentDetached(tag: String?) {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(tag)
        fragment?.let {
            fragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .remove(fragment)
                .commit()

            unlockDrawer()
        }
    }

    private fun setUp() {
        mDrawer = getViewDataBinding().drawerView
        mToolbar = getViewDataBinding().toolbar
        mNavigationView = getViewDataBinding().navigationView
        mCardsContainerView = getViewDataBinding().cardsContainer

        setSupportActionBar(mToolbar)
var a : IntIterator
        val mDrawerToggle = object :
            ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.open_drawer,
                R.string.close_drawer
            ) {

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                hideKeyboard()
            }
        }
        mDrawer.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()
        setupNavMenu()
        val version = getString(R.string.version) + BuildConfig.VERSION_NAME
        mMainViewModel.updateAppVersion(version)
        mMainViewModel.onNavMenuCreated()
        setupCardContainerView()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        mMainViewModel.getQuestionCardData().observe(this,
            Observer<MutableList<QuestionCardData>> { t -> mMainViewModel.setQuestionDataList(t) })
    }

    private fun setupCardContainerView() {
        val screenWidth = ScreenUtils.getScreenWidth(this)
        val screenHeight = ScreenUtils.getScreenHeight(this)

        mCardsContainerView.builder
            .setDisplayViewCount(3)
            .setHeightSwipeDistFactor(10f)
            .setWidthSwipeDistFactor(5f)
            .setSwipeDecor(
                SwipeDecor()
                    .setViewWidth((0.90 * screenWidth).toInt())
                    .setViewHeight((0.75 * screenHeight).toInt())
                    .setPaddingTop(20)
                    .setRelativeScale(0.01f)
                    .setSwipeRotationAngle(10)
            )

        mCardsContainerView.addItemRemoveListener { count ->
            when (count) {
                0 -> {
                    Handler(mainLooper).postDelayed({ mMainViewModel.loadQuestionCards() }, 800)
                }
                else -> mMainViewModel.removeQuestionCard()
            }
        }
    }

    private fun setupNavMenu() {
        val navHeaderMainBinding = DataBindingUtil.inflate<NavHeaderMainBinding>(
            layoutInflater,
            R.layout.nav_header_main, getViewDataBinding().navigationView, false
        )
        getViewDataBinding().navigationView.addHeaderView(navHeaderMainBinding.root)
        navHeaderMainBinding.viewModel = mMainViewModel

        mNavigationView.setNavigationItemSelectedListener { item: MenuItem ->
            mDrawer.closeDrawer(GravityCompat.START)
            when (item.itemId) {
                R.id.navItemAbout -> {
                    showAboutFragment()
                    return@setNavigationItemSelectedListener true
                }
                R.id.navItemRateUs -> {
                    RateUsDialog.newInstance().show(supportFragmentManager)
                    return@setNavigationItemSelectedListener true
                }
                R.id.navItemFeed -> {
                    startActivity(FeedActivity.newIntent(this@MainActivity))
                    return@setNavigationItemSelectedListener true
                }
                R.id.navItemLogout -> {
                    mMainViewModel.logout()
                    return@setNavigationItemSelectedListener true
                }
                else -> return@setNavigationItemSelectedListener false
            }
        }
    }

    private fun showAboutFragment() {
        lockDrawer()
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
            .add(R.id.clRootView, AboutFragment.newInstance(), AboutFragment.TAG)
            .commit()
    }

    private fun lockDrawer() {
        mDrawer?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun unlockDrawer() {
        mDrawer?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    override fun openLoginActivity() {
        startActivity(LoginActivity.newIntent(this))
        finish()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        fragmentDispatchingAndroidInjector

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
