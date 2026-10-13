package upca.example.mynews

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import upca.example.mynews.ui.theme.MyNewsTheme
import java.util.Date

@Composable
fun ArticlesView(
    modifier: Modifier = Modifier

){
    val viewModel : ArticlesViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()


    if (uiState.error!=null) {
        Text(uiState.error!!)
    }else {
        ArticlesViewContent(
            modifier = modifier,
            articles = uiState.articles
        )
    }


    LaunchedEffect(Unit) {
        viewModel.fetchArticles()
    }
}
@Composable
fun ArticlesViewContent(
    modifier: Modifier = Modifier,
    articles: List<Article> = arrayListOf()
){

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
                    .background(MaterialTheme.colorScheme.inversePrimary)
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
        ArticlesViewContent(
            articles = arrayListOf<Article>(
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
        )
    }
}