package upca.example.mynews

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.util.Date

data class ArticlesViewState(
    val articles : ArrayList<Article> = arrayListOf(),
    val error : String? = null,
    var isLoading : Boolean = false
)

class ArticlesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ArticlesViewState())
    val uiState : StateFlow<ArticlesViewState> = _uiState.asStateFlow()

    fun fetchArticles(){

        val client =  OkHttpClient()
        val  request =  Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?country=us&apiKey=1765f87e4ebc40229e80fd0f75b6416c")
            .build()

        _uiState.value = _uiState.value.copy(
            isLoading = true,
        )

        client.newCall(request).enqueue( object : Callback{
            override fun onFailure(call: Call, e: okio.IOException) {
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