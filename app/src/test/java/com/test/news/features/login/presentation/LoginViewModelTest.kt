package com.test.news.features.login.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.news.base.Event
import com.test.news.base.TestSchedulersTrampoline
import com.test.news.features.login.data.model.User
import com.test.news.features.login.domain.LoginInteractor
import com.test.news.features.login.domain.LoginResult
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var loginInteractor: LoginInteractor
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        viewModel = LoginViewModel(TestSchedulersTrampoline(), loginInteractor)
    }

    @Test
    fun shouldReduceViewStateOnLoginIntent() {
        // given
        val user = User(USER_NAME, USER_PASSWORD, true)
        val loginResult = LoginResult.LoginSucceed(user)
        val expectedViewState = LoginViewState(Event(loginResult))
        given(loginInteractor.loginWithCredentials(USER_NAME, USER_PASSWORD)).willReturn(Observable.just(loginResult))

        // when
        viewModel.dispatchIntent(LoginIntent.LoginUser(USER_NAME, USER_PASSWORD))

        // then
        assertEquals(expectedViewState, viewModel.viewState.value)
    }

    companion object {
        private const val USER_NAME = "user"
        private const val USER_PASSWORD = "password"
    }
}
