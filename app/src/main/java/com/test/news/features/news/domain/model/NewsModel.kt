package com.test.news.features.news.domain.model

sealed class NewsModel(open val imageUrlsList: List<String>) {
    data class Image(override val imageUrlsList: List<String>) : NewsModel(imageUrlsList)
    data class Slider(override val imageUrlsList: List<String>) : NewsModel(imageUrlsList)
}
