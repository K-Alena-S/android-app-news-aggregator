package com.example.newsaggregator.ui

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.newsaggregator.presenter.Article

@Composable
fun NewsItem(article: Article, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate(
                    "article/${Uri.encode(article.title)}/${Uri.encode(article.description)}/${Uri.encode(article.imageUrl ?: "")}"
                )
            }
    ) {
        article.imageUrl?.let { imageUrl ->
            val painter = rememberAsyncImagePainter(model = imageUrl)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = article.title,
            maxLines = 2,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = article.description,
            maxLines = 4,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}