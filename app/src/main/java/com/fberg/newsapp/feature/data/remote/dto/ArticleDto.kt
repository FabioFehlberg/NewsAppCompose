package com.fberg.newsapp.feature.data.remote.dto

import com.fberg.newsapp.feature.domain.model.Article
import java.text.SimpleDateFormat
import java.util.Locale

data class ArticleDto(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: ArticleSourceDto?,
    val title: String?,
    val url: String,
    val urlToImage: String?
)

fun ArticleDto.toArticle() = Article(description.orEmpty(), publishedAt.formatDate(), title.orEmpty(), url, urlToImage.orEmpty())

private fun String?.formatDate(from: String = "yyyy-MM-dd'T'HH:mm:ss'Z'", to: String = "dd/MM/yyyy"): String =
    try {
        this?.let {
            SimpleDateFormat(from, Locale.US).parse(this)?.let {
                SimpleDateFormat(to, Locale.US).format(it)
            }
        } ?: ""
    } catch (exception: Exception) {
        ""
    }
