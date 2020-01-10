package com.seven.mindorks.ui.splash

import android.os.Bundle
import com.seven.mindorks.BR
import com.seven.mindorks.R
import com.seven.mindorks.ViewModelProviderFactory
import com.seven.mindorks.databinding.ActivitySplashBinding
import com.seven.mindorks.ui.base.BaseActivity
import com.seven.mindorks.ui.login.LoginActivity
import com.seven.mindorks.ui.main.MainActivity
import javax.inject.Inject

/**
 * at 2019/12/3
 * at 14:30
 * summary:
 */
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewModel.setNavigator(this)
        splashViewModel.startSeeding()
    }

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun getViewModel(): SplashViewModel {
        splashViewModel = factory.create(SplashViewModel::class.java)
        return splashViewModel
    }

    override fun openLoginActivity() {
        val intent = LoginActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun openMainActivity() {
        val intent = MainActivity.newIntent(this)
        startActivity(intent)
        finish()
    }
}