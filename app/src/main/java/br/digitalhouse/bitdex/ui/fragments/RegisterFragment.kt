package br.digitalhouse.bitdex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.ui.interfaces.ToastInterface
import br.digitalhouse.bitdex.ui.viewmodel.AccessViewModel
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment(R.layout.fragment_register), ToastInterface {
    private val accessViewModel: AccessViewModel by viewModels()
    private lateinit var emailTxt: TextInputEditText
    private lateinit var passwordTxt: TextInputEditText
    private lateinit var registerbtn: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponentsView(view)
        setupListener()
        setupObserver()
    }

    private fun initComponentsView(view: View) {
        emailTxt = view.findViewById(R.id.editTextTextPersonName)
        passwordTxt = view.findViewById(R.id.textField)
        registerbtn = view.findViewById(R.id.button)
    }

    private fun setupListener() {
        registerbtn.setOnClickListener {
            accessViewModel.onCreateUser(emailTxt.text.toString(),passwordTxt.text.toString())
        }
    }
    private fun setupObserver() {
        accessViewModel.createUserLiveData.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                toast("Usuario Criado com sucesso!")
            } else {
                toast("Erro ao Criar usuário!")
            }
        }
    }

    override fun toast(toast: String) {
        Toast.makeText(requireContext(), toast, Toast.LENGTH_SHORT).show()
    }

}