package com.fberg.newsapp.feature.domain.model

data class Article(
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)
