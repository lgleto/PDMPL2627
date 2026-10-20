package upca.example.mynews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import upca.example.mynews.ui.theme.MyNewsTheme
import java.util.Date

@Composable
fun ArticleRowView(
    modifier: Modifier = Modifier,
    article : Article
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.inversePrimary)
    ) {
        Text(
            article.title ?: "",
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            article.description ?: "",
            style = MaterialTheme.typography.bodyLarge
        )
        article.urlToImage?.let {
            AsyncImage(
                model = it,
                contentDescription = "article image"
            )
        }?:run {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "article image"
            )
        }
        Text(article.publishedAt?.toShortDateTime()?:"")
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleRowViewPreview() {
    MyNewsTheme {
        ArticleRowView(
            article = Article(
                title = "Aulas de Computação Móvel",
                description = "fdjkhwk ehf wkehfkwefh kwuef",
                publishedAt = Date()
            )
        )
    }
}
