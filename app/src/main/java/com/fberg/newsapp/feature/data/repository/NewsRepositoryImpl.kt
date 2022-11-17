package com.fberg.newsapp.feature.data.repository

import com.fberg.newsapp.common.NewsException.DataException
import com.fberg.newsapp.common.Resource
import com.fberg.newsapp.feature.data.remote.NewsService
import com.fberg.newsapp.feature.data.remote.dto.toHeadlines
import com.fberg.newsapp.feature.domain.model.Headlines
import com.fberg.newsapp.feature.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsService: NewsService) : NewsRepository {
    override suspend fun getHeadlines(
        category: String?,
        search: String?,
        country: String?,
        pageSize: Int,
        page: Int
    ): Flow<Resource<Headlines>> = flow {
        try {
            emit(
                Resource.Success(
                    newsService.getTopHeadlines(category.orEmpty(), search.orEmpty(), country.orEmpty(), pageSize, page).toHeadlines()
                )
            )
        } catch (e: HttpException) {
            emit(Resource.Error(DataException(e.localizedMessage ?: "An unexpected error occurred")))
        } catch (e: IOException) {
            emit(Resource.Error(DataException("Couldn't reach server. Check your internet connection.")))
        }
    }
}
