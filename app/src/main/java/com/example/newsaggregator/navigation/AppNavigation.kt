package com.example.newsaggregator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsaggregator.ui.detail.WebViewScreen
import com.example.newsaggregator.ui.newslist.NewsListScreen
import com.example.newsaggregator.viewmodel.NewsListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val viewModel: NewsListViewModel = koinViewModel()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            NewsListScreen(viewModel = viewModel, navController = navController)
        }

        composable("webview/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            WebViewScreen(url = url, onBack = { navController.popBackStack() })
        }
    }
}