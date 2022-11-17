package com.fberg.newsapp.feature.presentation.web_view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fberg.newsapp.common.Constants.URL_PARAM
import dagger.hilt.android.lifecycle.HiltViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(WebViewState())
    val state: State<WebViewState> = _state

    init {
        savedStateHandle.get<String>(URL_PARAM)?.let { url ->
            val decodedUrl = URLDecoder.decode(url, StandardCharsets.UTF_8.toString())
            _state.value = _state.value.copy(
                url = decodedUrl
            )
        }
    }
}
