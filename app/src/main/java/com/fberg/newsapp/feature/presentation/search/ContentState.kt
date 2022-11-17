package com.fberg.newsapp.feature.presentation.search

sealed class ContentState {
    object Showing : ContentState()
    object Loading : ContentState()
    data class Error(val message: String) : ContentState()
    data class NoResults(val search: String) : ContentState()
}
