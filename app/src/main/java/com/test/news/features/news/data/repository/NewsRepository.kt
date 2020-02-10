package com.test.news.features.news.data.repository

import com.test.news.features.news.data.model.NewsItem
import com.test.news.features.news.data.model.NewsItemType
import com.test.news.features.news.data.model.WidgetType
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: NewsApi) {

    fun getSupportedNews(isPremium: Boolean): Observable<List<NewsItem>> = newsApi.fetchNews()
        .map { it.filter { newsItem -> isImageFiltered(newsItem, isPremium) || newsItem.type == WidgetType.SLIDER } }

    private fun isImageFiltered(newsItem: NewsItem, isPremium: Boolean) =
        newsItem.type == WidgetType.IMAGE && newsItem.dataType != getFilteredType(isPremium)

    private fun getFilteredType(premium: Boolean) = if (premium) NewsItemType.ADS else NewsItemType.PREMIUM_NEWS
}
