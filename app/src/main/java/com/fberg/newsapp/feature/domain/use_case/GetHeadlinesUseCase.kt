package com.fberg.newsapp.feature.domain.use_case

import com.fberg.newsapp.common.Constants.DEFAULT_PAGE_SIZE
import com.fberg.newsapp.common.NewsException.DomainException
import com.fberg.newsapp.common.Resource
import com.fberg.newsapp.feature.domain.model.Headlines
import com.fberg.newsapp.feature.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHeadlinesUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    private var currentPage = FIRST_PAGE
    private var lastCategory: String? = null
    private var lastSearch: String? = null
    private var lastCountry: String? = null

    suspend operator fun invoke(
        category: String?,
        search: String?,
        country: String,
        isRefresh: Boolean = false
    ): Flow<Resource<Headlines>> = flow {
        if (category.isNullOrBlank() && search.isNullOrBlank())
            emit(Resource.Error(DomainException("Required parameters are missing. Category and Search cannot both be null nor blank")))
        else {
            currentPage = when {
                isRefresh -> FIRST_PAGE
                category == lastCategory && search == lastSearch && country == lastCountry -> currentPage + 1
                else -> FIRST_PAGE
            }
            lastCategory = category
            lastSearch = search
            lastCountry = country
            repository.getHeadlines(lastCategory, lastSearch, lastCountry, DEFAULT_PAGE_SIZE, currentPage).collect {
                if (it is Resource.Success && currentPage == FIRST_PAGE) emit(Resource.Success(it.data, true))
                else emit(it)
            }
        }
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}
