package com.test.news.features.login.presentation

import com.test.news.base.Event
import com.test.news.features.login.domain.LoginResult

data class LoginViewState(
    val loginEvent: Event<LoginResult>
)
