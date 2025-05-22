package com.example.newsaggregator.ui.newslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import com.example.newsaggregator.R

@Composable
fun CategoryPanel(
    categories: List<String>,
    selectedCategory: String?,
    onCategorySelected: (String?) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val panelWidth = if (screenWidthDp / 2 < 500) {
        (screenWidthDp / 2).dp
    } else {
        500.dp
    }
    Surface(
        modifier = Modifier
            .width(panelWidth)
            .fillMaxHeight(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column {
            Text(
                text = stringResource(R.string.category_panel_name),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn {
                item {
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onCategorySelected(null)
                            },
                        headlineContent = { Text(text =
                            stringResource(R.string.show_all_articles_text)) },
                        colors = ListItemDefaults.colors(
                            containerColor = if (selectedCategory == null) MaterialTheme.colorScheme
                                .primaryContainer else MaterialTheme.colorScheme.surface
                        )
                    )
                }
                items(categories) { category ->
                    val isSelected = category == selectedCategory
                    ListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onCategorySelected(category)
                            },
                        headlineContent = { Text(text = category) },
                        colors = ListItemDefaults.colors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme
                                .primaryContainer else MaterialTheme.colorScheme.surface
                        )
                    )
                }
            }
        }
    }
}