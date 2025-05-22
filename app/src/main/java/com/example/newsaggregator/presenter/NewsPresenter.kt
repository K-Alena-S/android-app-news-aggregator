package com.example.newsaggregator.presenter

import com.example.newsaggregator.data.repository.NewsRepository
import com.example.newsaggregator.data.rss.dto.CategoryDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface NewsListView {
    fun showNews(news: List<Article>)
    fun showError(message: String)
}

class NewsPresenter(
    private val view: NewsListView,
    private val repository: NewsRepository
) {
    fun loadNews() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val rss = repository.fetchRss()
                val articles = rss.channel.items.map {
                    val largeImageUrl = it.contents.find { content ->
                        content.width == "460"
                    }?.url

                    Article(
                        title = it.title,
                        description = it.description,
                        link = it.link,
                        pubDate = it.pubDate,
                        guid = it.guid,
                        imageUrl = largeImageUrl,
                        category = it.categories
                    )
                }
                withContext(Dispatchers.Main) {
                    view.showNews(articles)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view.showError(e.message ?: "Error loading news")
                }
            }
        }
    }
}

data class Article(
    val title: String,
    val description: String,
    val link: String,
    val pubDate: String,
    val guid: String,
    val imageUrl: String?,
    val category: List<CategoryDto>
)