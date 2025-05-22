package com.example.newsaggregator.data.rss.dto

data class Article(
    val title: String,
    val description: String,
    val link: String,
    val pubDate: String,
    val guid: String,
    val imageUrl: String?,
    val category: List<CategoryDto>
)