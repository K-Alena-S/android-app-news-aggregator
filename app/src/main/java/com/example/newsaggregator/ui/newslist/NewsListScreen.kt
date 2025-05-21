package com.example.newsaggregator.ui.newslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavController
import com.example.newsaggregator.viewmodel.NewsListViewModel
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    viewModel: NewsListViewModel,
    navController: NavController
) {
    val articles = viewModel.articles
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    val listState = rememberLazyListState()
    val listStateGrid = rememberLazyGridState()

    LaunchedEffect(Unit) {
        viewModel.loadNews()
    }

    Column {
        NewsListTopBar()

        when {
            isLoading -> LoadingScreen()
            errorMessage != null -> ErrorScreen(errorMessage)
            else -> ContentScreen(
                articles = articles,
                screenWidthDp = screenWidthDp,
                listState = listState,
                listStateGrid = listStateGrid,
                navController = navController
            )
        }
    }
}



