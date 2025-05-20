package com.example.newsaggregator.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.newsaggregator.viewmodel.NewsListViewModel

@Composable
fun NewsListScreen(
    viewModel: NewsListViewModel,
    navController: NavController
) {
    val articles = viewModel.articles
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.loadNews()
    }

    when {
        isLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        errorMessage != null -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = errorMessage)
            }
        }
        else -> {
            LazyColumn (
                state = listState
            ){
                items(articles) { article ->
                    NewsItem(article, navController)
                }
            }
        }
    }
}

