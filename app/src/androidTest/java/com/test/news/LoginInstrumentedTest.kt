package com.test.news

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.test.news.features.login.presentation.LoginActivity
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class LoginInstrumentedTest {

    @get:Rule
    var activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    @Test
    fun shouldLoginWithValidCredentials() {
        onView(withId(R.id.editTextUserName))
            .perform(clearText(), typeText(VALID_USER_NAME))
        onView(withId(R.id.editTextPassword))
            .perform(clearText(), typeText(VALID_USER_PASSWORD))
        onView(withId(R.id.buttonLogin))
            .perform(click())

        // TODO assert login when ready
        assertTrue(activityTestRule.activity.isFinishing)
    }

    companion object {
        private const val VALID_USER_NAME = "user1"
        private const val VALID_USER_PASSWORD = "password"
    }
}
