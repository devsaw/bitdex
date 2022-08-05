package br.digitalhouse.bitdex.ui.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.databinding.FragmentLoginBinding
import br.digitalhouse.bitdex.ui.interfaces.ToastInterface
import br.digitalhouse.bitdex.ui.viewmodel.AccessViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment(R.layout.fragment_login), ToastInterface {
    var CONSUMER_KEY = "7qRdPwBOWkc57icZS1y1CVTce"
    var CONSUMER_SECRET = "OgEeB4j61wAF97BIx41fEKKOFDzbwfVy9UtJttEE8rBoRP7sc8"
    var CALLBACK_URL = "https://bitdex-7afd6.firebaseapp.com/__/auth/handler"

    private val accessViewModel: AccessViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding
    private lateinit var emailTxt: TextInputEditText
    private lateinit var passwordTxt: TextInputEditText
    private val firebaseAuth: FirebaseAuth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        setupListeners()
        setupObserver()
    }

    private fun initView(view: View) {
        emailTxt = view.findViewById(R.id.loginEditText)
        passwordTxt = view.findViewById(R.id.passwordEditText)
    }

    private fun setupListeners() {
        binding.btLogin.setOnClickListener {
            val emailTxt = emailTxt.text.toString()
            val passwordTxt = passwordTxt.text.toString()
            if (textIsBlank(emailTxt, passwordTxt)) {
                toast("Fill In The Fields!")
            } else {
                accessViewModel.onEmailSignIn(emailTxt, passwordTxt)
            }
        }

        binding.txtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnIntentCell.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_phoneFragment)
        }

        binding.btGoogle.setOnClickListener {
            val googleSignInIntent = accessViewModel.signInWithGoogle(requireActivity())
            startActivityForResult(googleSignInIntent, accessViewModel.GOOGLE_REQUEST_CODE)
        }

        binding.btnTwitter.setOnClickListener {
            accessViewModel.signInWithTwitter(requireActivity(), firebaseAuth)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == accessViewModel.GOOGLE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                accessViewModel.onGoogleSignInSucess(data!!)
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
                        toast("Erro ao logar!")
                    }
                }
            }
        }
    }

    private fun setupObserver() {
        accessViewModel.userAuthLiveData.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_loginFragment_to_appFragment)
                toast("Usuário logado!")
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

        accessViewModel.onUserRequestToGoogleSignInLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                toast("Usuário logado!")
            } else {
                toast("Falha ao logar!")
            }
        }
    }

    override fun toast(toast: String) {
        Toast.makeText(requireContext(), toast, Toast.LENGTH_SHORT).show()
    }

    private fun textIsBlank(login: String, password: String): Boolean {
        return login.isBlank() || password.isBlank()
    }
}