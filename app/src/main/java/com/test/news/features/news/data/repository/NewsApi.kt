package com.test.news.features.news.data.repository

import com.test.news.features.news.data.model.NewsItem
import io.reactivex.Observable
import retrofit2.http.GET

interface NewsApi {

    @GET("feed")
    fun fetchNews(): Observable<List<NewsItem>>
}
