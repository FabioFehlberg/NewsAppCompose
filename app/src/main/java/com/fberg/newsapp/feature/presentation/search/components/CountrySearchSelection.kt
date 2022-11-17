package com.fberg.newsapp.feature.presentation.search.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fberg.newsapp.feature.presentation.search.CountryForSearch

@Composable
fun CountrySearchSelection(
    onCountrySelected: (CountryForSearch) -> Unit
) {
    val scrollState = rememberScrollState()
    var countrySelected by remember { mutableStateOf(CountryForSearch.GERMANY) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .padding(end = 16.dp)
    ) {
        RadioButtonIcon(
            icon = CountryForSearch.NONE.icon,
            selected = countrySelected == CountryForSearch.NONE,
            onSelect = {
                countrySelected = CountryForSearch.NONE
                onCountrySelected(CountryForSearch.NONE)
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        RadioButtonIcon(
            icon = CountryForSearch.GERMANY.icon,
            selected = countrySelected == CountryForSearch.GERMANY,
            onSelect = {
                countrySelected = CountryForSearch.GERMANY
                onCountrySelected(CountryForSearch.GERMANY)
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        RadioButtonIcon(
            icon = CountryForSearch.UNITED_STATES.icon,
            selected = countrySelected == CountryForSearch.UNITED_STATES,
            onSelect = {
                countrySelected = CountryForSearch.UNITED_STATES
                onCountrySelected(CountryForSearch.UNITED_STATES)
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        RadioButtonIcon(
            icon = CountryForSearch.BRAZIL.icon,
            selected = countrySelected == CountryForSearch.BRAZIL,
            onSelect = {
                countrySelected = CountryForSearch.BRAZIL
                onCountrySelected(CountryForSearch.BRAZIL)
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        RadioButtonIcon(
            icon = CountryForSearch.AUSTRALIA.icon,
            selected = countrySelected == CountryForSearch.AUSTRALIA,
            onSelect = {
                countrySelected = CountryForSearch.AUSTRALIA
                onCountrySelected(CountryForSearch.AUSTRALIA)
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        RadioButtonIcon(
            icon = CountryForSearch.RUSSIA.icon,
            selected = countrySelected == CountryForSearch.RUSSIA,
            onSelect = {
                countrySelected = CountryForSearch.RUSSIA
                onCountrySelected(CountryForSearch.RUSSIA)
            }
        )
    }
}
