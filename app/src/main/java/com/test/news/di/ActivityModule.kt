package com.test.news.di

import com.test.news.features.login.presentation.LoginActivity
import com.test.news.features.news.presentation.NewsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module for contributing app activities
 */
@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun contributeNewsActivity(): NewsActivity
}
