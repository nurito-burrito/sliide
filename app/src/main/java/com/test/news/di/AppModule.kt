package com.test.news.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.news.BuildConfig
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Module for providing app related dependencies
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideApplicationContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideRxJava2CallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(interceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    companion object {
        const val CONNECTION_TIMEOUT_SECONDS = 20L
    }
}
