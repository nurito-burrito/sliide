package com.test.news

import android.app.Application
import com.test.news.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class NewsApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        initToolsForDebugOnly()
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

    private fun initToolsForDebugOnly() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun androidInjector() = dispatchingActivityInjector
}
