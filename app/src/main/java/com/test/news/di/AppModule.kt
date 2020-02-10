package com.test.news.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.news.BuildConfig
import com.test.news.base.DefaultSchedulersProvider
import com.test.news.base.SchedulersProvider
import com.test.news.db.AppDatabase
import com.test.news.db.UsersDao
import com.test.news.features.news.data.repository.NewsApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .createFromAsset(DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideUsersDao(database: AppDatabase): UsersDao {
        return database.usersDao()
    }

    @Singleton
    @Provides
    fun provideSchedulersProvider(): SchedulersProvider {
        return DefaultSchedulersProvider()
    }


    @Singleton
    @Provides
    fun provideMonitoredItemsApi(
        okHttpClient: OkHttpClient,
        gson: Gson,
        factory: CallAdapter.Factory
    ): NewsApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(factory)
            .build()
            .create(NewsApi::class.java)
    }

    companion object {
        private const val DATABASE_NAME = "users.db"
        private const val CONNECTION_TIMEOUT_SECONDS = 20L
    }
}
