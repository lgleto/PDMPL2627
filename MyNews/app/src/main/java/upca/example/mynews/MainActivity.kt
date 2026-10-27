package upca.example.mynews

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import upca.example.mynews.ui.components.MyBottomBar
import upca.example.mynews.ui.components.MyTopBar
import upca.example.mynews.ui.home.ArticlesView
import upca.example.mynews.ui.home.articledetail.ArticleDetailView
import upca.example.mynews.ui.theme.MyNewsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var title by remember { mutableStateOf( "My News")}
            var isBaseScreen by remember { mutableStateOf( true)}
            var action by remember { mutableStateOf<@Composable () -> Unit>( {} )}
            MyNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        MyTopBar(
                            title = title,
                            navController = navController,
                            isBaseScreen = isBaseScreen,
                            action = action
                        )
                    },
                    bottomBar = {
                        MyBottomBar(
                            navController = navController,
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Screen.Home.route
                    ){
                        composable(Screen.Home.route){
                            title = "My News"
                            isBaseScreen = true
                            action = {}
                            ArticlesView(
                                navController = navController,
                                category = "home"
                            )
                        }
                        composable(Screen.Business.route){
                            title = "Business"
                            isBaseScreen = true
                            action = {}
                            ArticlesView(
                                navController = navController,
                                category = "business"
                            )
                        }
                        composable(Screen.Sports.route){
                            title = "Sports"
                            isBaseScreen = true
                            action = {}
                            ArticlesView(
                                navController = navController,
                                category = "sports"
                            )
                        }
                        composable(Screen.Health.route){
                            title = "Health"
                            isBaseScreen = true
                            action = {}
                            ArticlesView(
                                navController = navController,
                                category = "health"
                            )
                        }

                        composable(Screen.ArticleDetail.route){ navBackStackEntry ->
                            title = navBackStackEntry.arguments?.getString("title")?:""
                            isBaseScreen = false
                            val url = navBackStackEntry.arguments?.getString("url")
                            action = {
                                IconButton(
                                    onClick = {
                                        val intentShare = Intent(Intent.ACTION_SEND)
                                        intentShare.setType("text/plain")
                                        intentShare.putExtra(Intent.EXTRA_SUBJECT,title)
                                        intentShare.putExtra(Intent.EXTRA_TEXT,url)
                                        startActivity(Intent.createChooser(intentShare,"Partilhar"))
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.arrow_up_from_bracket_solid_full),
                                        contentDescription = "button search"
                                    )
                                }
                            }

                            ArticleDetailView(
                                url = url
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route : String){
    object Home : Screen("home")
    object Business : Screen("business")
    object Health : Screen("health")
    object Sports : Screen("sports")
    object ArticleDetail : Screen("article_detail/{title}/{url}")
}
