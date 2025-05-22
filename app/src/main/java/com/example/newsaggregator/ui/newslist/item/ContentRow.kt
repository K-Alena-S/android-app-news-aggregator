package com.example.newsaggregator.ui.newslist.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.newsaggregator.data.rss.dto.Article

@Composable
fun ContentRow(article: Article, screenWidth: Dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val (imageWidth, descriptionWidth) = CalculateWidths(screenWidth)
        article.imageUrl?.let { imageUrl ->
            ImageSection(imageUrl, imageWidth)
        }
        DescriptionSection(article.description, descriptionWidth)
    }
}