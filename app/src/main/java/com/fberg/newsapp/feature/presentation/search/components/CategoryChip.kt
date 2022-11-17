package com.fberg.newsapp.feature.presentation.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fberg.newsapp.feature.presentation.categories.Category

@Composable
fun CategoryChip(category: Category) {
    Box(Modifier.padding(16.dp)) {
        Text(
            text = category.title,
            modifier = Modifier
                .background(
                    color = category.chipColor,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(8.dp)
        )
    }
}
