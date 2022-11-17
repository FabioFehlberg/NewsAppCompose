package com.fberg.newsapp.feature.presentation.web_view

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun WebViewScreen(
    viewModel: WebViewViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    if (state.url.isNotBlank()) {
        val webViewState = rememberWebViewState(url = state.url)
        WebView(state = webViewState, captureBackPresses = true)
    }
}
