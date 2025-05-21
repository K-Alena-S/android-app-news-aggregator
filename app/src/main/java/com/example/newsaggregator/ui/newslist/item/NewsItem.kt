package com.example.newsaggregator.ui.newslist.item

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsaggregator.presenter.Article
import androidx.compose.foundation.layout.*
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun NewsItem(article: Article, navController: NavController) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate(
                    "article/${Uri.encode(article.title)}/${Uri.encode(article.description)}/${Uri.encode(article.imageUrl ?: "")}"
                )
            }
    ) {
        TitleText(article.title)
        Spacer(modifier = Modifier.height(8.dp))
        ContentRow(article, screenWidth)
        Spacer(modifier = Modifier.height(8.dp))
        PubDateText(article.pubDate)
    }
}
