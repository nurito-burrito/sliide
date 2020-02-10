package com.test.news.features.news.data.model

import com.google.gson.annotations.SerializedName

data class NewsItem(
    val id: Int,
    val type: WidgetType,
    val title: String? = null,
    @SerializedName("deep_link")
    val deepLink: String? = null,
    val images: List<NewsItemImage>,
    @SerializedName("datatype")
    val dataType: NewsItemType? = null
)
