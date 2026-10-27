package upca.example.mynews.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import org.json.JSONObject
import upca.example.mynews.models.Article

data class ArticlesViewState(
    val articles : ArrayList<Article> = arrayListOf(),
    val error : String? = null,
    var isLoading : Boolean = false
)

class ArticlesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ArticlesViewState())
    val uiState : StateFlow<ArticlesViewState> = _uiState.asStateFlow()

    fun fetchArticles(
        category : String = "business"
    ){

        val urlString = when (category){
            "business" -> "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=1765f87e4ebc40229e80fd0f75b6416c"
            "sports" -> "https://newsapi.org/v2/top-headlines?country=us&category=sports&apiKey=1765f87e4ebc40229e80fd0f75b6416c"
            "health" -> "https://newsapi.org/v2/top-headlines?country=us&category=health&apiKey=1765f87e4ebc40229e80fd0f75b6416c"
            else -> "https://newsapi.org/v2/top-headlines?country=us&apiKey=1765f87e4ebc40229e80fd0f75b6416c"
        }

        val client =  OkHttpClient()
        val  request =  Request.Builder()
            .url(urlString)
            .build()

        _uiState.value = _uiState.value.copy(
            isLoading = true,
        )

        client.newCall(request).enqueue( object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                _uiState.value = _uiState.value.copy(
                    error =  e.toString(),
                    isLoading = false
                )
            }

            override fun onResponse(call: Call, response: Response) {
                response.use{
                    if (response.isSuccessful) {
                        val result = response.body.string()
                        val jsonResult = JSONObject(result)
                        val status = jsonResult.getString("status")
                        if (status == "ok"){
                            val articlesJson = jsonResult.getJSONArray("articles")
                            val articlesResult = arrayListOf<Article>()
                            for ( index in 0..<articlesJson.length()){
                                val articleJson = articlesJson.getJSONObject(index)
                                articlesResult.add(Article.fromJson(articleJson))
                            }
                            _uiState.value = _uiState.value.copy(
                                articles = articlesResult,
                                isLoading = false,
                                error = null
                            )
                        }
                    }else{
                        _uiState.value = _uiState.value.copy(
                            error = "falho o site",
                            isLoading = false
                        )
                    }
                }
            }
        })
    }

}