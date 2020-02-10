package com.test.news.features.login.domain

import androidx.annotation.IntDef
import com.test.news.features.login.domain.LoginFailReason.Companion.WRONG_PASSWORD
import com.test.news.features.login.domain.LoginFailReason.Companion.WRONG_USER_NAME

@IntDef(WRONG_USER_NAME, WRONG_PASSWORD)
@Retention(AnnotationRetention.SOURCE)
annotation class LoginFailReason {
    companion object {
        const val WRONG_USER_NAME = 0
        const val WRONG_PASSWORD = 1
    }
}
