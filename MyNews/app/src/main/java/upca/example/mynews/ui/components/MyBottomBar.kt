package upca.example.mynews.ui.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import upca.example.mynews.R
import upca.example.mynews.Screen
import upca.example.mynews.ui.theme.MyNewsTheme

data class BottomBarItem(
    val icon : Int,
    val label : String,
    val route : String
)

@Composable
fun MyBottomBar(
    navController : NavController = rememberNavController()
) {

    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(
        BottomBarItem(
            icon = R.drawable.baseline_home_24,
            label = "Home",
            route = Screen.Home.route
        ),
        BottomBarItem(
            icon = R.drawable.outline_attach_money_24,
            label = "Business",
            route = Screen.Business.route
        ),
        BottomBarItem(
            icon = R.drawable.outline_pickleball_24,
            label = "Sports",
            route = Screen.Sports.route
        ),
        BottomBarItem(
            icon = R.drawable.outline_ecg_heart_24,
            label = "Health",
            route = Screen.Health.route
        ),
    )

    BottomAppBar() {
        NavigationBar() {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedItem == index,
                    onClick = {
                        selectedItem = index
                        navController.navigate(item.route)
                    },
                    label = {
                        Text(item.label)
                    },
                    alwaysShowLabel = true,
                    icon = {
                        Icon(
                            painterResource(item.icon),
                            contentDescription = item.label
                        )
                    }
                )

            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun MyBottomBarPreview() {
    MyNewsTheme() {
        MyBottomBar()
    }
}