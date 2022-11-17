package com.fberg.newsapp.feature.presentation.search

import androidx.annotation.DrawableRes
import com.fberg.newsapp.R

enum class CountryForSearch(@DrawableRes val icon: Int, val searchParam: String) {
    AUSTRALIA(R.drawable.austallia_flag, "au"),
    BRAZIL(R.drawable.brazil_flag, "br"),
    GERMANY(R.drawable.germany_flag, "de"),
    RUSSIA(R.drawable.russia_flag, "ru"),
    UNITED_STATES(R.drawable.united_flag, "us"),
    NONE(R.drawable.globe_free_icon, "")
}
