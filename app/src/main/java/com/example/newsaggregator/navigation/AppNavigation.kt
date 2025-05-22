package com.example.newsaggregator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsaggregator.ui.detail.WebViewScreen
import com.example.newsaggregator.ui.newslist.NewsListScreen
import com.example.newsaggregator.viewmodel.NewsListViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val viewModel: NewsListViewModel = viewModel()

    NavHost(navController = navController, startDestination = "list") {
        // Экран списка новостей
        composable("list") {
            NewsListScreen(viewModel = viewModel, navController = navController)
        }

        // Новый экран с WebView
        composable("webview/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            WebViewScreen(url = url, onBack = { navController.popBackStack() })
        }
    }
}