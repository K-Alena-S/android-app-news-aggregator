package com.example.newsaggregator.ui.newslist

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.newsaggregator.data.rss.dto.Article

@Composable
fun ContentScreen(
    articles: List<Article>,
    screenWidthDp: Int,
    listState: LazyListState,
    listStateGrid: LazyGridState,
    navController: NavController
) {
    if (screenWidthDp > 500) {
        NewsGrid(articles, listStateGrid, navController)
    } else {
        NewsList(articles, listState, navController)
    }
}