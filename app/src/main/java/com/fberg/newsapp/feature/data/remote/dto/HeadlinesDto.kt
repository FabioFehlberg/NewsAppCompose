package com.fberg.newsapp.feature.data.remote.dto

import com.fberg.newsapp.feature.domain.model.Headlines

data class HeadlinesDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)

fun HeadlinesDto.toHeadlines() = Headlines(articles.map { it.toArticle() })
