package com.test.news.features.news.domain

import com.test.news.features.news.domain.model.NewsModel

sealed class NewsResult {
    object Loading : NewsResult()
    object Error : NewsResult()
    data class News(val photosList: List<NewsModel>) : NewsResult()
}
