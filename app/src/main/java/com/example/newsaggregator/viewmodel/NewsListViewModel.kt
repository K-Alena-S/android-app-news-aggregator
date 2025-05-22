package com.example.newsaggregator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.data.repository.NewsRepository
import com.example.newsaggregator.data.rss.dto.Article
import kotlinx.coroutines.launch

class NewsListViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val allArticles = mutableListOf<Article>()

    private val _articles = mutableStateListOf<Article>()
    val articles: List<Article> get() = _articles

    private val _categories = mutableStateListOf<String>()
    val categories: List<String> get() = _categories

    private var _isLoading by mutableStateOf(false)
    val isLoading: Boolean get() = _isLoading

    private var _errorMessage by mutableStateOf<String?>(null)
    val errorMessage: String? get() = _errorMessage

    private var _selectedCategory: String? by mutableStateOf(null)
    val selectedCategory: String? get() = _selectedCategory

    fun loadNews() {
        _isLoading = true
        _errorMessage = null
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                val news = repository.fetchRss().channel.items.map {
                    val largeImageUrl = it.contents.find { content -> content.width == "460" }?.url
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
                allArticles.clear()
                allArticles.addAll(news)
                updateCategories(news)
                filterArticles()
            } catch (e: Exception) {
                _errorMessage = e.message ?: "Error loading news"
            } finally {
                _isLoading = false
            }
        }
    }

    fun selectCategory(category: String?) {
        _selectedCategory = category
        filterArticles()
    }

    private fun updateCategories(articles: List<Article>) {
        val categoriesSet = mutableSetOf<String>()
        articles.forEach { article ->
            article.category.forEach { cat ->
                categoriesSet.add(cat.value)
            }
        }
        _categories.clear()
        _categories.addAll(categoriesSet.sorted())
    }

    private fun filterArticles() {
        val filtered = if (_selectedCategory == null || _selectedCategory == "Show All Articles") {
            allArticles
        } else {
            allArticles.filter { it.category.any { c -> c.value == _selectedCategory } }
        }
        _articles.clear()
        _articles.addAll(filtered)
    }
}