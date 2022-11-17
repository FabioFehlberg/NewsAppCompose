package com.fberg.newsapp.feature.presentation

sealed class FeatureScreens(val route: String) {
    object CategoriesScreen : FeatureScreens("categories_screen")
    object SearchScreen : FeatureScreens("search_screen")
    object WebViewScreen : FeatureScreens("web_view_screen")
}
