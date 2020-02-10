package com.test.news.features.news.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.test.news.R
import com.test.news.features.news.domain.model.NewsModel
import com.test.news.features.news.presentation.NewsIntent.GetNews
import com.test.news.features.news.presentation.adapter.NewsWidgetsAdapter
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_news.*
import java.lang.IllegalStateException
import java.lang.NullPointerException
import javax.inject.Inject

class NewsActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var newsAdapter: NewsWidgetsAdapter

    private lateinit var viewModel: NewsViewModel

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[NewsViewModel::class.java]
        newsAdapter = NewsWidgetsAdapter {
            if(isPremium()){
                throw NullPointerException("Image url cannot be null")
            } else  {
                startActivity(Intent.parseUri(it.imageUrlsList.first(), Intent.URI_INTENT_SCHEME))
            }
        }
        setContentView(R.layout.activity_news)
        recyclerViewNews.adapter = newsAdapter
        initViewModel()
        setTitle()
    }

    private fun setTitle() {
        if(isPremium()) title = "$title Prenium"
    }

    private fun initViewModel() {
        viewModel.viewState.observe(this, Observer { viewState ->
            renderState(viewState)
        })
        viewModel.dispatchInitialIntent(GetNews(isPremium()))
    }

    private fun isPremium() = intent.getBooleanExtra(KEY_IS_PREMIUM, false)

    private fun renderState(viewState: NewsViewState) {
        when {
            viewState.isLoading -> showLoading()
            viewState.isError -> showError()
            viewState.newsList.isNotEmpty() -> showNews(viewState.newsList)
        }
    }

    private fun showLoading() {
        recyclerViewNews.visibility = View.GONE
        textViewError.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun showError() {
        progressBar.visibility = View.GONE
        textViewError.visibility = View.VISIBLE
    }

    private fun showNews(newsList: List<NewsModel>) {
        progressBar.visibility = View.GONE
        recyclerViewNews.visibility = View.VISIBLE
        newsAdapter.newsItems = newsList
        newsAdapter.notifyDataSetChanged()
    }

    companion object {
        private const val KEY_IS_PREMIUM = "key_is_premium"

        fun getStartIntent(context: Context, isPremium: Boolean): Intent {
            return Intent(context, NewsActivity::class.java).apply {
                putExtra(KEY_IS_PREMIUM, isPremium)
            }
        }
    }
}
