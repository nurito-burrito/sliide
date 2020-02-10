package com.test.news.features.login.presentation

/**
 * Intents for [LoginViewModel]
 * If there's only one intent like here sealed class is excessive and could be skipped, but
 * from the architecture point of view it shows that all the intents go here
 */
sealed class LoginIntent {
    data class LoginUser(val userName: String, val password: String) : LoginIntent()
    // Add other intents for login screen here
}
