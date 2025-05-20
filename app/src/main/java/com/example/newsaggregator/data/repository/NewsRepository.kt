package com.example.newsaggregator.data.repository

import com.example.newsaggregator.data.rss.RssFeed
import com.example.newsaggregator.data.rss.dto.RssDto

interface NewsRepository {
    suspend fun fetchRss(): RssDto
}
class NewsRepositoryImpl(private val rssService: RssFeed): NewsRepository {
    override suspend fun fetchRss(): RssDto {
        return rssService.getRss()
    }
}