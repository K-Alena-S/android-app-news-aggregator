package com.example.newsaggregator.ui.newslist

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import android.text.Spanned
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.text.HtmlCompat

@Composable
fun SimpleHtmlContent(
    html: String,
    maxLines: Int? = null
) {
    val spannedText: Spanned = HtmlCompat.fromHtml(
        html,
        HtmlCompat.FROM_HTML_MODE_LEGACY
    )

    val annotatedString = buildAnnotatedString { append(spannedText.toString()) }

    val colors = MaterialTheme.colorScheme

    Text(
        text = annotatedString,
        color = colors.onBackground,
        maxLines = maxLines ?: Int.MAX_VALUE,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.fillMaxWidth()
    )
}