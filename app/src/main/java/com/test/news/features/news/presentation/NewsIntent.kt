package com.test.news.features.news.presentation

sealed class NewsIntent {
    data class GetNews(val isPremium: Boolean) : NewsIntent()
}
