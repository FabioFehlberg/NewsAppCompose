package com.fberg.newsapp.feature.presentation.search

sealed class SearchEvents {
    data class Search(val search: String? = null, val country: CountryForSearch) : SearchEvents()
    object LoadMore : SearchEvents()
    object Refresh : SearchEvents()
    object ToggleCountryVisibility : SearchEvents()
}
