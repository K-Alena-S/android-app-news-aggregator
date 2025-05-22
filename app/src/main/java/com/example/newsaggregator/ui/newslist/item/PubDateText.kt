package com.example.newsaggregator.ui.newslist.item

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun PubDateText(pubDate: String) {
    val colors = MaterialTheme.colorScheme
    Text(
        text = pubDate,
        color = colors.onBackground,
        fontSize = 12.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End)
    )
}