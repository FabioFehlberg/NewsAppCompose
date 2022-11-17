package com.fberg.newsapp.feature.presentation.search.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.fberg.newsapp.feature.domain.model.Article
import com.fberg.newsapp.feature.presentation.search.SearchEvents
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

private const val LIST_REFRESH_MORE_THRESHOLD = 2

@Composable
fun ArticleList(
    articles: List<Article>,
    isEndOfList: Boolean,
    onSearchEvent: (SearchEvents) -> Unit,
    onItemClick: (Article) -> Unit
) {
    val listState = rememberLazyListState()
    val swipeRefreshState = rememberSwipeRefreshState(false)
    var lastLoadMoreItemsCount by remember { mutableStateOf(0) }

    if (articles.size < lastLoadMoreItemsCount) lastLoadMoreItemsCount = 0

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { onSearchEvent(SearchEvents.Refresh) }
    ) {
        LazyColumn(state = listState) {
            articles.forEach {
                item { ArticleListItem(it, onItemClick) }
            }
            if (listState.isScrolledToTheEnd() && !isEndOfList && articles.size > lastLoadMoreItemsCount) {
                lastLoadMoreItemsCount = articles.size
                onSearchEvent(SearchEvents.LoadMore)
            }
        }
    }
}

fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1 - LIST_REFRESH_MORE_THRESHOLD

@Preview(showBackground = true)
@Composable
fun PreviewArticleList() {
    val article = Article(
        "Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description ",
        "20/02/2022",
        "Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title ",
        "www.url.com",
        "www.urlimage.com"
    )
    val list = listOf(article, article, article, article, article, article, article, article, article, article, article)
    ArticleList(articles = list, false, onSearchEvent = {}) {}
}
