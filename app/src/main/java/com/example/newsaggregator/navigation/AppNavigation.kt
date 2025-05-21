package com.example.newsaggregator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsaggregator.ui.detail.ArticleDetailScreen
import com.example.newsaggregator.ui.newslist.NewsListScreen
import com.example.newsaggregator.viewmodel.NewsListViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        // Экран списка новостей
        composable("list") {
            val viewModel = remember { NewsListViewModel() }
            NewsListScreen(viewModel, navController)
        }

        // Экран с деталями новости
        composable(
            "article/{title}/{description}/{imageUrl}",
            arguments = listOf(
                navArgument("title") { type = androidx.navigation.NavType.StringType },
                navArgument("description") { type = androidx.navigation.NavType.StringType },
                navArgument("imageUrl") { type = androidx.navigation.NavType.StringType }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""
            val imageUrl = backStackEntry.arguments?.getString("imageUrl") ?: ""
            ArticleDetailScreen(title, description, imageUrl, onBack = { navController.popBackStack() })
        }
    }
}