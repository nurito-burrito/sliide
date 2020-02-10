package com.test.news.features.login.domain

import com.test.news.features.login.data.UsersRepository
import com.test.news.features.login.data.model.User
import com.test.news.features.login.domain.LoginResult.*
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginInteractorTest {

    @Mock
    private lateinit var usersRepository: UsersRepository
    private lateinit var loginInteractor: LoginInteractor
    private val user = User(VALID_USER_NAME, VALID_USER_PASSWORD, true)
    private val validUsers = listOf(user)

    @Before
    fun setUp() {
        loginInteractor = LoginInteractor(usersRepository)
    }

    @Test
    fun shouldSucceedLogin() {
        // given
        given(usersRepository.getUsers()).willReturn(Observable.just(validUsers))

        // when
        val testObserver = loginInteractor.loginWithCredentials(VALID_USER_NAME, VALID_USER_PASSWORD).test()

        // then
        testObserver.assertValue(LoginSucceed(user))
    }

    @Test
    fun shouldFailLoginWithWrongUserName() {
        // given
        given(usersRepository.getUsers()).willReturn(Observable.just(validUsers))

        // when
        val testObserver = loginInteractor.loginWithCredentials(INVALID_USER_NAME, VALID_USER_PASSWORD).test()

        // then
        testObserver.assertValue(LoginFailed(LoginFailReason.WRONG_USER_NAME))
    }

    @Test
    fun shouldFailLoginWithWrongPassword() {
        // given
        given(usersRepository.getUsers()).willReturn(Observable.just(validUsers))

        // when
        val testObserver = loginInteractor.loginWithCredentials(VALID_USER_NAME, INVALID_USER_PASSWORD).test()

        // then
        testObserver.assertValue(LoginFailed(LoginFailReason.WRONG_PASSWORD))
    }

    companion object {
        private const val VALID_USER_NAME = "valid user"
        private const val INVALID_USER_NAME = "invalid user"
        private const val VALID_USER_PASSWORD = "valid password"
        private const val INVALID_USER_PASSWORD = "invalid password"
    }
}
