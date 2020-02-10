package com.test.news.features.news.presentation

import com.test.news.features.news.domain.model.NewsModel

data class NewsViewState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val newsList: List<NewsModel> = emptyList()
)
