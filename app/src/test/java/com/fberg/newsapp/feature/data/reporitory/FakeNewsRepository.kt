package com.fberg.newsapp.feature.data.reporitory

import com.fberg.newsapp.common.NewsException
import com.fberg.newsapp.common.Resource
import com.fberg.newsapp.feature.domain.model.Article
import com.fberg.newsapp.feature.domain.model.Headlines
import com.fberg.newsapp.feature.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNewsRepository : NewsRepository {
    override suspend fun getHeadlines(
        category: String?,
        search: String?,
        country: String?,
        pageSize: Int,
        page: Int
    ): Flow<Resource<Headlines>> = flow {
        when {
            category == "FakeDataException" -> emit(Resource.Error(NewsException.DataException("")))
            page == 2 -> emit(Resource.Success(headlinesForPage2))
            else -> emit(Resource.Success(headlines))
        }
    }

    private val article = Article(
        description = "",
        publishedAt = "",
        title = "",
        url = "",
        urlToImage = ""
    )

    private val headlines = Headlines(listOf(article))
    private val headlinesForPage2 = Headlines(listOf(article, article))
}
