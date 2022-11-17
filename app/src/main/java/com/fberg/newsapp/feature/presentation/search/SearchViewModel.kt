package com.fberg.newsapp.feature.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fberg.newsapp.common.Constants.CATEGORY_PARAM
import com.fberg.newsapp.common.Constants.DEFAULT_PAGE_SIZE
import com.fberg.newsapp.common.Resource
import com.fberg.newsapp.feature.domain.model.Headlines
import com.fberg.newsapp.feature.domain.use_case.GetHeadlinesUseCase
import com.fberg.newsapp.feature.presentation.categories.Category
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getHeadlinesUseCase: GetHeadlinesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    private var getHeadlinesJob: Job? = null

    init {
        savedStateHandle.get<String>(CATEGORY_PARAM)?.let {
            val category = Gson().fromJson(it, Category::class.java)
            _state.value = _state.value.copy(
                category = category
            )
            onEvent(SearchEvents.Search(country = state.value.countrySelected))
        }
    }

    fun onEvent(event: SearchEvents) {
        when (event) {
            is SearchEvents.ToggleCountryVisibility -> toggleCountrySelectionState()
            is SearchEvents.LoadMore -> onLoadMoreEvent()
            is SearchEvents.Refresh -> onRefreshEvent()
            is SearchEvents.Search -> onSearchEvent(event.search, event.country)
        }
    }

    private fun onLoadMoreEvent() {
        if (state.value.isLoadingMore) return
        _state.value = _state.value.copy(
            isLoadingMore = true
        )
        getHeadlines()
    }

    private fun onRefreshEvent() {
        if (state.value.isLoadingMore) return
        _state.value = _state.value.copy(
            contentState = ContentState.Loading
        )
        getHeadlines(isRefresh = true)
    }

    private fun onSearchEvent(search: String?, country: CountryForSearch) {
        _state.value = _state.value.copy(
            search = search,
            countrySelected = country,
            contentState = ContentState.Loading
        )
        getHeadlines()
    }

    private fun toggleCountrySelectionState() {
        _state.value = _state.value.copy(
            isCountrySelectionVisible = !state.value.isCountrySelectionVisible
        )
    }

    private fun getHeadlines(isRefresh: Boolean = false) {
        getHeadlinesJob?.cancel()
        getHeadlinesJob = viewModelScope.launch {
            getHeadlinesUseCase(
                state.value.category?.title,
                state.value.search,
                state.value.countrySelected.searchParam,
                isRefresh
            ).collect { resource ->
                when (resource) {
                    is Resource.Success -> onGetHeadlinesSuccess(resource.data, resource.shouldResetList)
                    is Resource.Error -> onGetHeadlinesError(resource.exception.errorMessage)
                }
            }
        }
    }

    private fun onGetHeadlinesSuccess(headlines: Headlines, shouldResetList: Boolean) {
        _state.value = if (_state.value.articles.isEmpty() && headlines.articles.isEmpty())
            _state.value.copy(
                contentState = ContentState.NoResults(state.value.search.orEmpty()),
                isLoadingMore = false
            )
        else if (headlines.articles.size < DEFAULT_PAGE_SIZE)
            _state.value.copy(
                contentState = ContentState.Showing,
                isEndOfList = true,
                isLoadingMore = false
            ).apply {
                if (shouldResetList) articles.clear()
                articles.addAll(headlines.articles)
            }
        else _state.value.copy(
            isEndOfList = false,
            contentState = ContentState.Showing,
            isLoadingMore = false
        ).apply {
            if (shouldResetList) articles.clear()
            articles.addAll(headlines.articles)
        }
    }

    private fun onGetHeadlinesError(errorMessage: String) {
        _state.value = _state.value.copy(
            contentState = ContentState.Error(errorMessage),
            isLoadingMore = false
        )
    }
}
