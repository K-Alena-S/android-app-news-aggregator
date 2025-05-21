package com.example.newsaggregator.ui.newslist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
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
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()
    ) {
        items(articles) { article ->
            NewsItem(article, navController)
        }
    }
}