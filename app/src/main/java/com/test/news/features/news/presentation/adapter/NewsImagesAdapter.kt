package com.test.news.features.news.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.news.R
import com.test.news.base.GlideApp
import com.test.news.features.news.domain.model.NewsModel
import kotlinx.android.synthetic.main.layout_single_image.view.*

class NewsImagesAdapter(
    private val newsModel: NewsModel,
    private val imageUrls: List<String>,
    private val itemClicked: (NewsModel) -> Unit
) : RecyclerView.Adapter<NewsImagesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_single_image, parent, false)
        view.setOnClickListener { itemClicked(newsModel) }
        return ViewHolder(view)
    }

    override fun getItemCount() = imageUrls.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        GlideApp.with(holder.view.imageView)
            .load(imageUrls[position])
            .into(holder.view.imageView)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
