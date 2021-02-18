package com.test.news.login

import com.test.news.R
import com.test.news.common.BaseTestRobot
import com.test.news.common.Launch


fun login(func: LoginRobot.() -> Unit) = LoginRobot().apply {
    func()
}

fun login(launch: Launch, func: LoginRobot.() -> Unit) = LoginRobot().apply {
    launch.launch()
    func()
}

class LoginRobot : BaseTestRobot() {


    fun tapOnLoginButton() {
        tapOnButton(R.id.buttonLogin)
    }

    fun enterInvalidUsername() {
        fillEditText(
            resId = R.id.editTextUserName,
            text = INVALID_USER_NAME
        )
    }

    fun enterValidPassword() {
        fillEditText(
            resId = R.id.editTextPassword,
            text = VALID_USER_PASSWORD
        )
    }

    fun clickUsernameField() {
        tapOnButton(R.id.editTextUserName)
    }

    fun clickPasswordField() {
        tapOnButton(R.id.editTextPassword)
    }

    fun invalidUsernameValidPassword() {
        enterInvalidUsername()
        enterValidPassword()
        tapOnLoginButton()
        clickUsernameField()
    }

    fun validLoginCredentials() {
        fillEditText(resId = R.id.editTextUserName, text = VALID_USER_NAME)
        fillEditText(resId = R.id.editTextPassword, text = VALID_USER_PASSWORD)
        tapOnLoginButton()
    }

    infix fun result(func: ResultRobot.() -> Unit): ResultRobot {
        return ResultRobot().apply { func() }
    }

    class ResultRobot : BaseTestRobot() {

        fun loginScreenElementsCheck() {
            viewIsVisible(R.id.editTextUserName)
            viewIsVisible(R.id.editTextPassword)
            viewIsVisible(R.id.buttonLogin)
        }

        fun wrongUsernameAlertIsShown() {
            stringIsVisibleIsDescendant(
                text = "Wrong user name",
                resId = R.id.textViewError
            )
        }
    }
}