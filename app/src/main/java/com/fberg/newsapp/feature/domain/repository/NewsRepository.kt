package com.fberg.newsapp.feature.domain.repository

import com.fberg.newsapp.common.Resource
import com.fberg.newsapp.feature.domain.model.Headlines
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getHeadlines(category: String?, search: String?, country: String?, pageSize: Int, page: Int): Flow<Resource<Headlines>>
}
