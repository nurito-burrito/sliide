package com.test.news.features.login.presentation

import com.test.news.base.BaseViewModel
import com.test.news.base.Event
import com.test.news.base.SchedulersProvider
import com.test.news.features.login.domain.LoginInteractor
import com.test.news.features.login.domain.LoginResult
import io.reactivex.Observable
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    schedulersProvider: SchedulersProvider,
    private val loginInteractor: LoginInteractor
) : BaseViewModel<LoginResult, LoginViewState, LoginIntent>(schedulersProvider) {

    override fun handleIntent(intent: LoginIntent): Observable<LoginResult> = when (intent) {
        is LoginIntent.LoginUser -> loginInteractor.loginWithCredentials(intent.userName, intent.password)
    }

    override fun reduceViewState(result: LoginResult) = LoginViewState(loginEvent = Event(result))
}
