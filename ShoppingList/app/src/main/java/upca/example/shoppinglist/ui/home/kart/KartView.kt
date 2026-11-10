package upca.example.myshoppinglist.ui.home.kart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import upca.example.shoppinglist.Screen
import upca.example.shoppinglist.ui.home.kart.KartViewModel
import upca.example.shoppinglist.ui.home.kart.ProductViewRow
import upca.example.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun KartView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    kartId : String
){

    val viewModel : KartViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(kartId) {
        viewModel.fetchKart(kartId = kartId)
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        if (uiState.isLoading) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Loading products...   ")
            }
        } else if (uiState.products.isEmpty()) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No products found!")
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(
                    items = uiState.products,
                ) { _, product ->
                   ProductViewRow(product = product){ value ->
                        viewModel.updateProduct(productId = product.id!!, checked = value)
                   }
                }
            }
        }

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                navController.navigate(
                    Screen.AddProduct.route
                        .replace("{kartId}", kartId)
                )
            }) {
            Text("Add Product")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun KartViewPreview() {
    ShoppingListTheme() {
        KartView(
            kartId = "no id"
        )
    }
}