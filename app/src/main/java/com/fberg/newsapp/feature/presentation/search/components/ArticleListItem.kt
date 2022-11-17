package com.fberg.newsapp.feature.presentation.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fberg.newsapp.R
import com.fberg.newsapp.feature.domain.model.Article

@Composable
fun ArticleListItem(
    article: Article,
    onClick: (Article) -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        elevation = 2.dp,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
            .clickable { onClick(article) }
            .fillMaxWidth()
            .height(130.dp)
    ) {
        Row(Modifier.padding(8.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                if (article.urlToImage.isBlank())
                    Image(
                        painter = painterResource(R.drawable.ic_newspaper_mockup_free),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                    )
                else
                    ImagePicasso(
                        url = article.urlToImage,
                        modifier = Modifier.size(80.dp)
                    )
                Text(
                    text = stringResource(R.string.published_label, article.publishedAt),
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = article.title,
                    maxLines = 2,
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.secondary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = article.description,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArticleListItem() {
    ArticleListItem(
        Article(
            "Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description Description ",
            "20/02/2022 - 15:02:59",
            "Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title Title ",
            "www.url.com",
            "www.urlimage.com"
        )
    ) {}
}
