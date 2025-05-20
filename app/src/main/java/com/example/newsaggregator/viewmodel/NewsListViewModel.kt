package com.example.newsaggregator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.newsaggregator.data.repository.NewsRepository
import com.example.newsaggregator.data.repository.NewsRepositoryImpl
import com.example.newsaggregator.data.rss.RssFeed
import com.example.newsaggregator.presenter.Article
import com.example.newsaggregator.presenter.NewsListView
import com.example.newsaggregator.presenter.NewsPresenter
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import nl.adaptivity.xmlutil.serialization.XML
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class NewsListViewModel : ViewModel(), NewsListView {
    private val _articles = mutableStateListOf<Article>()
    val articles: List<Article> get() = _articles

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.theguardian.com")
        .addConverterFactory(
            XML.asConverterFactory(
                "application/xml; charset=UTF8".toMediaType()
            )
        ).build()


    private val guardian = retrofit.create(RssFeed::class.java)

    private val repository: NewsRepository = NewsRepositoryImpl(guardian)
    private val presenter = NewsPresenter(this, repository)

    fun loadNews() {
        isLoading = true
        errorMessage = null
        presenter.loadNews()
    }

    override fun showNews(news: List<Article>) {
        _articles.clear()
        _articles.addAll(news)
        isLoading = false
    }

    override fun showError(message: String) {
        errorMessage = message
        isLoading = false
    }
}