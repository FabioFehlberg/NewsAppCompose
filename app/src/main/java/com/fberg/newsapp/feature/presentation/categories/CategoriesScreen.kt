package com.fberg.newsapp.feature.presentation.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fberg.newsapp.R
import com.fberg.newsapp.feature.presentation.FeatureScreens
import com.fberg.newsapp.feature.presentation.categories.components.CategoryCard
import com.fberg.newsapp.ui.theme.LightBlue
import com.fberg.newsapp.ui.theme.LightBrown
import com.fberg.newsapp.ui.theme.LightGreen
import com.fberg.newsapp.ui.theme.LightOrange
import com.fberg.newsapp.ui.theme.LightRed
import com.fberg.newsapp.ui.theme.LightYellow
import com.fberg.newsapp.ui.theme.Purple200
import com.google.gson.Gson

@Composable
fun CategoriesScreen(
    navController: NavController
) {

    val categories = listOf(
        Category(stringResource(R.string.business_category), R.drawable.business_free_icon, Purple200),
        Category(stringResource(R.string.entertainment_category), R.drawable.entertainment_free_icon, LightGreen),
        Category(stringResource(R.string.general_category), R.drawable.general_free_icon, LightYellow),
        Category(stringResource(R.string.health_category), R.drawable.health_free_icon, LightOrange),
        Category(stringResource(R.string.science_category), R.drawable.science_free_icon, LightRed),
        Category(stringResource(R.string.sports_category), R.drawable.sports_free_icon, LightBlue),
        Category(stringResource(R.string.technology_category), R.drawable.technology_free_icon, LightBrown),
    )
    val categoryIsEven = categories.size.mod(2) == 1
    val pair = mutableListOf<Category>()

    Column {
        TopAppBar(
            title = {
                Image(
                    painter = painterResource(R.drawable.ic_logo_free_icon),
                    contentDescription = null
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 28.sp,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                )
            },
            backgroundColor = MaterialTheme.colors.secondaryVariant
        )

        Text(
            stringResource(id = R.string.categories_label),
            fontSize = 24.sp,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(start = 32.dp, top = 16.dp)
        )

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxSize()
        ) {
            categories.forEachIndexed { index, category ->
                val isLastSingleItem = (categoryIsEven && ((index + 1) == categories.size))
                pair.add(category)
                if (pair.size == 2) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CategoryCard(category = pair[0]) {
                            val categoryParam = Gson().toJson(it)
                            navController.navigate(FeatureScreens.SearchScreen.route + "/$categoryParam")
                        }
                        CategoryCard(category = pair[1]) {
                            val categoryParam = Gson().toJson(it)
                            navController.navigate(FeatureScreens.SearchScreen.route + "/$categoryParam")
                        }
                    }
                    pair.clear()
                } else if (isLastSingleItem) {
                    CategoryCard(category = pair[0]) {
                        val categoryParam = Gson().toJson(it)
                        navController.navigate(FeatureScreens.SearchScreen.route + "/$categoryParam")
                    }
                }
            }
        }
    }
}
