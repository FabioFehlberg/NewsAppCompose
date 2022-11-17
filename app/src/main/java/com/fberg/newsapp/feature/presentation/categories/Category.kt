package com.fberg.newsapp.feature.presentation.categories

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import java.io.Serializable

data class Category(val title: String, @DrawableRes val icon: Int, val chipColor: Color) : Serializable
