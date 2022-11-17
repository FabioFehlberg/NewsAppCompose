package com.fberg.newsapp.feature.presentation.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fberg.newsapp.feature.presentation.FeatureScreens
import com.fberg.newsapp.feature.presentation.search.components.ArticleList
import com.fberg.newsapp.feature.presentation.search.components.CategoryChip
import com.fberg.newsapp.feature.presentation.search.components.CountrySearchSelection
import com.fberg.newsapp.feature.presentation.search.components.NoResults
import com.fberg.newsapp.feature.presentation.search.components.SearchError
import com.fberg.newsapp.feature.presentation.search.components.SearchImeActionInput
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(Modifier.fillMaxWidth()) {
        SearchImeActionInput(state.countrySelected.icon, onCountryClick = {
            viewModel.onEvent(SearchEvents.ToggleCountryVisibility)
        }) {
            if (it.isBlank())
                viewModel.onEvent(SearchEvents.Search(country = state.countrySelected))
            else
                viewModel.onEvent(SearchEvents.Search(it, country = state.countrySelected))
        }
        AnimatedVisibility(
            visible = state.isCountrySelectionVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier.fillMaxWidth()
        ) {
            CountrySearchSelection(
                onCountrySelected = { country ->
                    viewModel.onEvent(SearchEvents.Search(state.search, country))
                }
            )
        }
        state.category?.let { CategoryChip(category = it) }
        when (val contentState = state.contentState) {
            is ContentState.Error -> SearchError(contentState.message) {
                viewModel.onEvent(SearchEvents.Refresh)
            }
            is ContentState.NoResults -> NoResults(contentState.search)
            ContentState.Loading -> CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
            ContentState.Showing -> ArticleList(state.articles, isEndOfList = state.isEndOfList, onSearchEvent = { event ->
                if (event is SearchEvents.LoadMore && !state.isLoadingMore) viewModel.onEvent(event)
                else if (event is SearchEvents.Refresh) viewModel.onEvent(event)
            }) {
                if (it.url.isNotBlank()) {
                    val encodedUrl = URLEncoder.encode(it.url, StandardCharsets.UTF_8.toString())
                    navController.navigate(FeatureScreens.WebViewScreen.route + "/$encodedUrl")
                }
            }
        }
    }
}
