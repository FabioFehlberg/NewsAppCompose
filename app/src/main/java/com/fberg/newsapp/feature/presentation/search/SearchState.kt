package com.fberg.newsapp.feature.presentation.search

import com.fberg.newsapp.feature.domain.model.Article
import com.fberg.newsapp.feature.presentation.categories.Category

data class SearchState(
    val search: String? = null,
    val category: Category? = null,
    val isEndOfList: Boolean = false,
    val isLoadingMore: Boolean = false,
    val isCountrySelectionVisible: Boolean = false,
    val countrySelected: CountryForSearch = CountryForSearch.GERMANY,
    val contentState: ContentState = ContentState.Loading,
    val articles: MutableList<Article> = mutableListOf()
)
