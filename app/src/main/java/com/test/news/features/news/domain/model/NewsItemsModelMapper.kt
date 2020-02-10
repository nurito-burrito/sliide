package com.test.news.features.news.domain.model

import com.test.news.features.news.data.model.NewsItem
import com.test.news.features.news.data.model.WidgetType
import javax.inject.Inject

class NewsItemsModelMapper @Inject constructor() {

    fun map(newsItems: List<NewsItem>) = mutableListOf<NewsModel>().apply {
        newsItems.forEach { add(mapNewsItem(it, newsItems)) }
    }.toList()

    private fun mapNewsItem(item: NewsItem, newsItems: List<NewsItem>) = when (item.type) {
        WidgetType.SLIDER -> mapToSliderModel(item, newsItems)
        WidgetType.IMAGE -> NewsModel.Image(item.images.map { it.url })
    }

    private fun mapToSliderModel(sliderItem: NewsItem, allItems: List<NewsItem>): NewsModel.Slider {
        val sliderImageIds = sliderItem.images.map { it.id }
        val sliderImageUrls = allItems.filter { sliderImageIds.contains(it.id) }.map { it.images.first().url }
        return NewsModel.Slider(sliderImageUrls)
    }
}
