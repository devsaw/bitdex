package br.digitalhouse.bitdex.ui.viewmodel

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.digitalhouse.bitdex.ui.model.User
import br.digitalhouse.bitdex.ui.model.UserDAO
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.ktx.Firebase

class AccessViewModel : ViewModel() {
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var gso: GoogleSignInOptions
    val GOOGLE_REQUEST_CODE = 1000


    private var onUserRequestToRegister = MutableLiveData<Boolean>()
    var createUserLiveData: LiveData<Boolean> = onUserRequestToRegister

    private var onUserRequestToSignIn = MutableLiveData<Boolean>()
    var userAuthLiveData: LiveData<Boolean> = onUserRequestToSignIn

    private var authResponse = MutableLiveData<AuthResult?>()
    var authResponseLiveData: LiveData<AuthResult?> = authResponse


    fun onCreateUser(email: String, password: String?) {
        if (password != null) {
            val registerTask = firebaseAuth.createUserWithEmailAndPassword(email, password)

            registerTask.addOnCompleteListener {
                if (registerTask.isSuccessful) {
                    val user = User(firebaseAuth.currentUser?.uid!!, email, password)
                    UserDAO().insertUser(user)
                    onUserRequestToRegister.value = registerTask.isSuccessful
                }
            }
        } else {
            val user = User(firebaseAuth.currentUser?.uid!!, email, password)
            UserDAO().insertUser(user)
            onUserRequestToSignIn.value = true
        }
    }

    fun onEmailSignIn(email: String, password: String) {
        val authTask = firebaseAuth.signInWithEmailAndPassword(email, password)

        authTask.addOnCompleteListener {
            onUserRequestToSignIn.value = authTask.isSuccessful
        }
    }

    fun signInGoogleConfig(activity: Activity): Intent {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("714940683119-5h52kmokd3kneh2cn9nk48guicoji8n8.apps.googleusercontent.com")
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(activity, gso).signInIntent
    }

    fun signInWithTwitter(activity: Activity, firebaseAuth: FirebaseAuth) {
        val pendingResult = FirebaseAuth.getInstance().pendingAuthResult
        if (pendingResult != null) {
            pendingResult.addOnSuccessListener { authResult ->
                authResponse.postValue(authResult)
            }.addOnFailureListener {
                Log.e("twitter", "Falha ao autenticar", it)
                authResponse.postValue(null)
            }
        } else {
            val oAuthProvider = OAuthProvider.newBuilder("twitter.com")
            firebaseAuth.startActivityForSignInWithProvider(activity, oAuthProvider.build())
                .addOnSuccessListener { authResult ->
                    authResponse.postValue(authResult)
                }.addOnFailureListener {
                    Log.e("twitter", "Falha ao autenticar", it)
                    authResponse.postValue(null)
                }
        }
    }
}