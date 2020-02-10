package com.test.news.features.news.domain

import com.test.news.features.news.data.repository.NewsRepository
import com.test.news.features.news.domain.model.NewsItemsModelMapper
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class NewsInteractor @Inject constructor(
    private val repository: NewsRepository,
    private val modelMapper: NewsItemsModelMapper
) {

    fun getNews(premium: Boolean): Observable<NewsResult> = repository.getSupportedNews(premium)
        .map<NewsResult> { NewsResult.News(modelMapper.map(it)) }
        .onErrorReturn {
            Timber.e(it)
            NewsResult.Error
        }
        .startWith(NewsResult.Loading)

}
