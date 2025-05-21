package com.example.newsaggregator.ui.newslist.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.newsaggregator.ui.SimpleHtmlContent

@Composable
fun DescriptionSection(description: String, width: Dp) {
    Column(
        modifier = Modifier
            .width(width)
            .fillMaxHeight()
            .padding(start = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        SimpleHtmlContent(html = description, maxLines = 8)
    }
}