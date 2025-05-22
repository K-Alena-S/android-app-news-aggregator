package com.example.newsaggregator.ui.newslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.newsaggregator.presenter.Article
import com.example.newsaggregator.ui.newslist.item.NewsItem

@Composable
fun NewsList(
    articles: List<Article>,
    listState: LazyListState,
    navController: NavController
) {
    val colors = MaterialTheme.colorScheme
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize().background(colors.background)
    ) {
        items(articles) { article ->
            NewsItem(article, navController)
        }
    }
}