package com.test.news.features.news.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.test.news.R
import com.test.news.features.news.domain.model.NewsModel
import com.test.news.features.news.domain.model.NewsModel.Image
import com.test.news.features.news.domain.model.NewsModel.Slider

class NewsWidgetsAdapter(
    private val itemClicked: (NewsModel) -> Unit
) : RecyclerView.Adapter<NewsWidgetsAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    var newsItems: List<NewsModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val recyclerView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_image_widget, parent, false) as RecyclerView
        return ViewHolder(recyclerView)
    }

    override fun getItemCount(): Int {
        return newsItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = newsItems[position]
        holder.recyclerView.apply {
            holder.snapHelper?.attachToRecyclerView(null)
            holder.snapHelper =
                createSnapHelperForType(newsItem).also { it.attachToRecyclerView(this) }
            layoutManager =
                LinearLayoutManager(holder.recyclerView.context, RecyclerView.HORIZONTAL, false)
            adapter = NewsImagesAdapter(newsItem, newsItem.imageUrlsList, itemClicked)
            setRecycledViewPool(viewPool)
        }
    }

    private fun createSnapHelperForType(newsModel: NewsModel) = when (newsModel) {
        is Image -> LinearSnapHelper()
        is Slider -> PagerSnapHelper()
    }

    inner class ViewHolder(val recyclerView: RecyclerView) : RecyclerView.ViewHolder(recyclerView) {
        var snapHelper: SnapHelper? = null
    }
}
