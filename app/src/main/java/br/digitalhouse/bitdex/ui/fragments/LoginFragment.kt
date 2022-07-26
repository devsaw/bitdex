package br.digitalhouse.bitdex.ui.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.ui.interfaces.ToastInterface
import br.digitalhouse.bitdex.ui.viewmodel.AccessViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.SignInButton
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.auth.TwitterAuthCredential
import com.google.firebase.database.logging.DefaultLogger
import java.lang.Exception

class LoginFragment : Fragment(R.layout.fragment_login), ToastInterface {
    var CONSUMER_KEY = "nl06shGjmc0odwt8affvgs6LN"
    var CONSUMER_SECRET = "Zj4m8Z995II382CkmWhutEyv2pM0NWEqn7OrJpxJR2h9atmgT2"
    var CALLBACK_URL = "https://bitdex-7afd6.firebaseapp.com/__/auth/handler"

    private val accessViewModel: AccessViewModel by viewModels()
    private lateinit var emailTxt: TextInputEditText
    private lateinit var passwordTxt: TextInputEditText
    private lateinit var signInbtn: Button
    private lateinit var register: TextView
    private lateinit var googleBtn: SignInButton
    private lateinit var phoneBtn: Button
    private lateinit var twitterBtn: Button
    lateinit var firebaseAuth: FirebaseAuth


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        setupListeners()
        setupObserver()
    }

    private fun initView(view: View) {
        emailTxt = view.findViewById(R.id.loginEditText)
        passwordTxt = view.findViewById(R.id.passwordEditText)
        signInbtn = view.findViewById(R.id.btLogin)
        register = view.findViewById(R.id.txtRegister)
        phoneBtn = view.findViewById(R.id.btnIntentCell)
        googleBtn = view.findViewById(R.id.btGoogle)
        twitterBtn = view.findViewById(R.id.btnTwitter)
    }

    private fun setupListeners() {
        signInbtn.setOnClickListener {
            accessViewModel.onEmailSignIn(emailTxt.text.toString(), passwordTxt.text.toString())
        }

        register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        phoneBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_phoneFragment)
        }

        googleBtn.setOnClickListener {
            val googleSignInIntent = accessViewModel.signInGoogleConfig(requireActivity())
            startActivityForResult(googleSignInIntent, accessViewModel.GOOGLE_REQUEST_CODE)
        }

        twitterBtn.setOnClickListener {
            accessViewModel.signInWithTwitter(requireActivity(),firebaseAuth)
        }
    }

    private fun setupObserver() {
        accessViewModel.userAuthLiveData.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                toast("Usuário cadastrado!")
            } else {
                toast("Erro, verifique os dados!")
            }
        }

        accessViewModel.authResponseLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.action_loginFragment_to_appFragment)
                toast("Usuário logado!")
            } else {
                toast("Falha ao logar!")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == accessViewModel.GOOGLE_REQUEST_CODE) {
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            val credential = GoogleAuthProvider.getCredential(accountTask.result.idToken, null)
            firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = accountTask.result
                    accessViewModel.onCreateUser(
                        user.email.toString(),
                        null
                    )
                } else {
                    toast("Falha ao logar!")
                }
            }
        }
    }

    override fun toast(toast: String) {
        Toast.makeText(requireContext(), toast, Toast.LENGTH_SHORT).show()
    }
}