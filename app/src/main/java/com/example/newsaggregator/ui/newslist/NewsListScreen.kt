package com.example.newsaggregator.ui.newslist

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import androidx.navigation.NavController
import com.example.newsaggregator.viewmodel.NewsListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    viewModel: NewsListViewModel,
    navController: NavController
) {
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    val listState = rememberLazyListState()
    val listStateGrid = rememberLazyGridState()

    var isRefreshing by remember { mutableStateOf(false) }
    var showCategoryPanel by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadNews()
    }

    fun refreshNews() {
        isRefreshing = true
        viewModel.loadNews()
        isRefreshing = false
    }

    val gestureModifier = Modifier.pointerInput(Unit) {
        detectHorizontalDragGestures { _, dragAmount ->
            if (dragAmount > 50) {
                showCategoryPanel = true
            } else if (dragAmount < -50) {
                showCategoryPanel = false
            }
        }
    }

    Box(
        modifier = gestureModifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            NewsListTopBar()

            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = { refreshNews() }
            ) {
                when {
                    isLoading -> LoadingScreen()
                    errorMessage != null -> ErrorScreen(
                        errorMessage,
                        onRetry = {
                            viewModel.loadNews()
                        }
                    )
                    else -> ContentScreen(
                        articles = viewModel.articles,
                        screenWidthDp = screenWidthDp,
                        listState = listState,
                        listStateGrid = listStateGrid,
                        navController = navController
                    )
                }
            }
        }

        if (showCategoryPanel) {
            CategoryPanel(
                categories = viewModel.categories,
                selectedCategory = viewModel.selectedCategory,
                onCategorySelected = { category ->
                    viewModel.selectCategory(category)
                    showCategoryPanel = false
                }
            )
        }
    }
}