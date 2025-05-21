package com.example.newsaggregator.ui.newslist.item

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CalculateWidths(screenWidth: Dp): Pair<Dp, Dp> {
    var imageWidth = screenWidth * 3 / 5
    var descriptionWidth = screenWidth - imageWidth
    if (screenWidth > 500.dp) {
        imageWidth /= 2
        descriptionWidth /= 2
    }
    return Pair(imageWidth, descriptionWidth)
}