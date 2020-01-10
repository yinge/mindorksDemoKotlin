package com.seven.mindorks.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.seven.mindorks.BR
import com.seven.mindorks.R
import com.seven.mindorks.ViewModelProviderFactory
import com.seven.mindorks.databinding.ActivityLoginBinding
import com.seven.mindorks.ui.base.BaseActivity
import com.seven.mindorks.ui.main.MainActivity
import com.seven.mindorks.utils.showToast
import javax.inject.Inject

/**
 * at 2019/12/3
 * at 9:55
 * summary:
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel.setNavigator(this)
    }

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun getViewModel(): LoginViewModel {
        loginViewModel = factory.create(LoginViewModel::class.java)
        return loginViewModel
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }

    override fun handleError(throwable: Throwable) {

    }

    override fun login() {
        val email = getViewDataBinding().etEmail.text.toString()
        val password = getViewDataBinding().etPassword.text.toString()
        if (loginViewModel.isEmailAndPasswordValid(email, password)) {
            hideKeyboard()
            loginViewModel.login(email, password)
        } else {
            showToast(R.string.invalid_email_password)
        }
    }

    override fun openMainActivity() {
        val intent = MainActivity.newIntent(this)
        startActivity(intent)
        finish()
    }
}