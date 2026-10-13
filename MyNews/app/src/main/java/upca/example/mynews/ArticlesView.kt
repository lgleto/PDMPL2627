package upca.example.mynews

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import upca.example.mynews.ui.theme.MyNewsTheme
import java.util.Date

@Composable
fun ArticlesView(
    modifier: Modifier = Modifier
){
    var articles = arrayListOf<Article>(
        Article(
            title = "Aulas de Computação Móvel",
            description = "fdjkhwk ehf wkehfkwefh kwuef",
            publishedAt = Date()
        ),
        Article(
            title = "Escandalo, o Braga foi roubado frente ao Sporting",
            description = "fdjkhwk ehf wkehfkwefh kwuef",
            publishedAt = Date()
        ),
        Article(
            title = "Escandalo, o Ronaldo quer voltar à selecção",
            description = "fdjkhwk ehf wkehfkwefh kwuef",
            publishedAt = Date()
        )
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        itemsIndexed(
            items = articles
        ){ index, article->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Text(article.title ?: "",
                    style = MaterialTheme.typography.titleLarge,
                    )
                Text(article.description ?: "",
                    style = MaterialTheme.typography.bodyLarge)
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "article image"
                )
                Text(article.publishedAt?.toString() ?: "")
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ArticlesViewPreview(){
    MyNewsTheme() {
        ArticlesView()
    }
}