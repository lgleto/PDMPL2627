package upca.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import upca.example.myshoppinglist.ui.home.kart.KartView
import upca.example.shoppinglist.ui.components.MyTopBar
import upca.example.shoppinglist.ui.home.HomeView
import upca.example.shoppinglist.ui.home.kart.AddKartView
import upca.example.shoppinglist.ui.home.kart.AddProductView
import upca.example.shoppinglist.ui.login.LoginView
import upca.example.shoppinglist.ui.theme.ShoppingListTheme


const val TAG = "shoppinglist"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            var title by remember { mutableStateOf("My Shopping List") }
            var isBaseScreen by remember { mutableStateOf(true) }
            var topBarActions by remember {mutableStateOf<@Composable () -> Unit>({})}

            LaunchedEffect(Unit) {
                val currentUser = Firebase.auth.currentUser
                if (currentUser != null) {
                    navController.navigate(Screen.Home.route)
                } else {
                    navController.navigate(Screen.Login.route)
                }
            }

            ShoppingListTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                        topBar = {
                    MyTopBar(
                        navController = navController,
                        title = title,
                        isBaseScreen = isBaseScreen,
                        actions = topBarActions
                    )
                },
                ) { innerPadding ->
                    NavHost(navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)) {
                        composable (Screen.Home.route){
                            isBaseScreen = true
                            title = "Home"
                            topBarActions = {
                                Text("Logout",
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clickable
                                        {
                                            Firebase.auth.signOut()
                                            navController.navigate(Screen.Login.route)
                                        }
                                )
                            }
                            HomeView(
                                navController = navController
                            )
                        }
                        composable (Screen.AddKart.route){
                            isBaseScreen = false
                            title = "Add Kart"
                            topBarActions = {}
                            AddKartView(
                                navController = navController
                            )
                        }
                        composable (Screen.Kart.route){ backStateEntry ->
                            val kartId = backStateEntry.arguments?.getString("kartId")
                            isBaseScreen = false
                            title = "Kart"
                            topBarActions = {}
                            KartView(
                                navController = navController,
                                kartId = kartId!!
                            )
                        }
                        composable (Screen.AddProduct.route){ backStateEntry ->
                            val kartId = backStateEntry.arguments?.getString("kartId")
                            isBaseScreen = false
                            title = "Add Product"
                            topBarActions = {}
                            AddProductView(
                                navController = navController,
                                kartId = kartId!!
                            )
                        }
                        composable (Screen.Login.route){
                            isBaseScreen = true
                            title = ""
                            topBarActions = {}
                            LoginView(navController = navController)
                        }
                    }
                }
            }
        }
    }
}



sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object AddKart : Screen("add_kart")
    object Kart : Screen("kart/{kartId}")
    object AddProduct : Screen ("add_product/{kartId}")
}
