package upca.example.shoppinglist.ui.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import upca.example.shoppinglist.TAG


data class LoginState (
    var email : String? = null,
    var password : String? = null,
    var error : String? = null,
    var isLoading : Boolean = false
)

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState : StateFlow<LoginState> = _uiState.asStateFlow()

    fun updateEmail(email : String){
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun updatePassword(password : String){
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun login(onSuccess : () -> Unit){
        val auth = Firebase.auth

        _uiState.value = _uiState.value.copy(isLoading = true)

        if (_uiState.value.email.isNullOrEmpty()) {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                error = "Email is required")
            return
        }

        if (_uiState.value.password.isNullOrEmpty()) {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                error = "Password is required")
            return
        }

        auth.signInWithEmailAndPassword(
            _uiState.value.email!!,
            _uiState.value.password!!)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    //updateUI(user)
                    _uiState.value = _uiState.value.copy(
                        isLoading = false
                    )
                    onSuccess()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = task.exception?.localizedMessage)
                }
            }
    }



}