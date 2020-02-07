package com.test.news.di

import com.test.news.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module for contributing app activities
 */
@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
