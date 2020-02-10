package com.test.news.features.news.presentation

import com.test.news.base.BaseViewModel
import com.test.news.base.SchedulersProvider
import com.test.news.features.news.domain.NewsInteractor
import com.test.news.features.news.domain.NewsResult
import io.reactivex.Observable
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    schedulersProvider: SchedulersProvider,
    private val newsInteractor: NewsInteractor
) :
    BaseViewModel<NewsResult, NewsViewState, NewsIntent>(schedulersProvider) {

    override fun handleIntent(intent: NewsIntent): Observable<NewsResult> = when (intent) {
        is NewsIntent.GetNews -> newsInteractor.getNews(intent.isPremium)
    }

    override fun reduceViewState(result: NewsResult): NewsViewState = when (result) {
        NewsResult.Loading -> NewsViewState(isLoading = true)
        NewsResult.Error -> NewsViewState(isError = true)
        is NewsResult.News -> NewsViewState(newsList = result.photosList)
    }
}
