package com.fberg.newsapp.feature.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fberg.newsapp.common.Constants.CATEGORY_PARAM
import com.fberg.newsapp.common.Constants.URL_PARAM
import com.fberg.newsapp.feature.presentation.categories.CategoriesScreen
import com.fberg.newsapp.feature.presentation.search.SearchScreen
import com.fberg.newsapp.feature.presentation.web_view.WebViewScreen
import com.fberg.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = FeatureScreens.CategoriesScreen.route
                ) {
                    composable(
                        route = FeatureScreens.CategoriesScreen.route
                    ) {
                        CategoriesScreen(navController)
                    }
                    composable(
                        route = FeatureScreens.SearchScreen.route + "/{$CATEGORY_PARAM}"
                    ) {
                        SearchScreen(navController)
                    }
                    composable(
                        route = FeatureScreens.WebViewScreen.route + "/{$URL_PARAM}"
                    ) {
                        WebViewScreen()
                    }
                }
            }
        }
    }
}
