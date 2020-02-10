package com.test.news.features.login.domain

import com.test.news.features.login.data.UsersRepository
import com.test.news.features.login.data.model.User
import com.test.news.features.login.domain.LoginResult.*
import io.reactivex.Observable
import javax.inject.Inject

class LoginInteractor @Inject constructor(
    private val usersRepository: UsersRepository
) {

    fun loginWithCredentials(userName: String, password: String): Observable<LoginResult> =
        usersRepository.getUsers().map { usersList ->
            usersList
                .find { user -> user.userName == userName }
                ?.let { getResultForFoundUser(it, password) }
                ?: LoginFailed(LoginFailReason.WRONG_USER_NAME)
        }

    private fun getResultForFoundUser(user: User, password: String) =
        if (user.password == password) LoginSucceed(user)
        else LoginFailed(LoginFailReason.WRONG_PASSWORD)
}
