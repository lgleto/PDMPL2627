package upca.example.shoppinglist.ui.home.kart

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import upca.example.shoppinglist.models.Product


data class AddProductViewState (
    var productName : String = "",
    var productQuantity : Double = 0.0,
    var error : String? = null,
    var isLoading : Boolean = false
    )

class AddProductViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AddProductViewState())
    val uiState: StateFlow<AddProductViewState> = _uiState.asStateFlow()

    fun editProductName(name: String){
        _uiState.value = _uiState.value.copy(
            productName = name)
    }

    fun editProductQuantity(value: Double){
        _uiState.value = _uiState.value.copy(
            productQuantity = value)
    }

    fun addProduct (kartId : String) {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error =  null)

        val db = Firebase.firestore

        val product = Product(
            name = _uiState.value.productName,
            quantity = _uiState.value.productQuantity,
            checked = false
        )

        db.collection("karts")
            .document(kartId)
            .collection("products")
            .add(product)
    }
}
