package com.example.newsaggregator.ui.newslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.newsaggregator.presenter.Article
import com.example.newsaggregator.ui.newslist.item.NewsItem

@Composable
fun NewsGrid(
    articles: List<Article>,
    gridState: LazyGridState,
    navController: NavController
) {
    val colors = MaterialTheme.colorScheme
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = gridState,
        modifier = Modifier.fillMaxSize().background(colors.background)
    ) {
        items(articles) { article ->
            NewsItem(article, navController)
        }
    }
}