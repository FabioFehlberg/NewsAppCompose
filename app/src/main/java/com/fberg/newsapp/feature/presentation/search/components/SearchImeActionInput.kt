package com.fberg.newsapp.feature.presentation.search.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fberg.newsapp.R
import com.fberg.newsapp.common.hideKeyboard
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchImeActionInput(
    @DrawableRes countryIcon: Int,
    onCountryClick: () -> Unit,
    onValueChange: (String) -> Unit
) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
    ) {
        var text by remember { mutableStateOf("") }

        var isTyping by remember { mutableStateOf(false) }

        val coroutineScope = rememberCoroutineScope()
        var typingDelayedJob: Job? = null

        OutlinedTextField(
            value = text,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    hideKeyboard(context)
                }
            ),
            onValueChange = {
                text = it
                if (typingDelayedJob?.isCompleted != false && !isTyping) {
                    typingDelayedJob?.cancel()
                    typingDelayedJob = coroutineScope.launch {
                        delay(500L)
                        onValueChange(text)
                        isTyping = false
                    }
                }
                isTyping = true
            },
            trailingIcon = {
                IconButton(onClick = { onCountryClick() }) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(countryIcon),
                        contentDescription = null
                    )
                }
            },
            label = { Text(stringResource(R.string.search)) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colors.primaryVariant,
                unfocusedLabelColor = MaterialTheme.colors.primaryVariant,
                focusedBorderColor = MaterialTheme.colors.primaryVariant,
                focusedLabelColor = MaterialTheme.colors.primaryVariant,
                cursorColor = MaterialTheme.colors.primaryVariant,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchImeActionInput() {
    SearchImeActionInput(
        R.drawable.globe_free_icon,
        onCountryClick = {}
    ) {}
}
