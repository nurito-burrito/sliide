package com.test.news.features.login.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.test.news.R
import com.test.news.features.login.domain.LoginFailReason
import com.test.news.features.login.domain.LoginFailReason.Companion.WRONG_PASSWORD
import com.test.news.features.login.domain.LoginFailReason.Companion.WRONG_USER_NAME
import com.test.news.features.login.domain.LoginResult
import com.test.news.features.login.presentation.LoginIntent.LoginUser
import com.test.news.features.news.presentation.NewsActivity
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: LoginViewModel

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_login)
        initLoginButton()
        initViewModel()
    }

    private fun initLoginButton() {
        buttonLogin.setOnClickListener {
            viewModel.dispatchIntent(LoginUser(editTextUserName.text.toString(), editTextPassword.text.toString()))
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]
        viewModel.viewState.observe(this, Observer { viewState ->
            renderState(viewState)
        })
    }

    private fun renderState(viewState: LoginViewState) {
        viewState.loginEvent.getContentIfNotHandled()?.let {
            handleEvent(it)
        }
    }

    private fun handleEvent(loginResult: LoginResult) {
        when (loginResult) {
            is LoginResult.LoginFailed -> showLoginError(loginResult.failReason)
            is LoginResult.LoginSucceed -> {
                startActivity(NewsActivity.getStartIntent(this, loginResult.user.isPremium))
                finish()
            }
        }
    }

    private fun showLoginError(@LoginFailReason loginFailReason: Int) {
        when (loginFailReason) {
            WRONG_USER_NAME -> editTextUserName.error = getString(R.string.login_wrong_user_name_error)
            WRONG_PASSWORD -> editTextPassword.error = getString(R.string.login_wrong_password_error)
        }
    }
}
