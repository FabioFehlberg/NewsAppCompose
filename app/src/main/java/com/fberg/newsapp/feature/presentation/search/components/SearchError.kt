package com.fberg.newsapp.feature.presentation.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fberg.newsapp.R

@Composable
fun SearchError(message: String, onRefreshClick: () -> Unit) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.sad_face_free_icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.onSurface, CircleShape)
            )
            Spacer(Modifier.height(36.dp))
            Text(
                message,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onSurface
            )
            Spacer(Modifier.height(36.dp))
            Button(
                onClick = { onRefreshClick() },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colors.onSurface,
                    backgroundColor = MaterialTheme.colors.secondaryVariant
                )
            ) {
                Text(stringResource(R.string.refresh_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchError() {
    SearchError(message = "There was an error.") {}
}
