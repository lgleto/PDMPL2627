package upca.example.mynews

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import upca.example.mynews.ui.theme.MyNewsTheme
import java.util.Date


@Composable
fun ArticleDetailView(
    modifier: Modifier = Modifier,
    title : String?,
    url : String?,
){
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            title ?: "",
            style = MaterialTheme.typography.titleLarge,
        )
        AndroidView(
            modifier = Modifier
                .fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()
                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true
                    settings.setSupportZoom(true)
                    settings.builtInZoomControls = true
                }
            },
            update = { webView ->
                webView.loadUrl(url?:"")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleDetailViewPreview() {
    MyNewsTheme {
        ArticleDetailView(
            title = "Aulas de Computação Móvel",
            url = "https://ipca.pt"
        )
    }
}
