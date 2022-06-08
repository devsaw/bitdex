package br.digitalhouse.bitdex.ui.fragments


import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.digitalhouse.bitdex.R

class LoginFragment : Fragment(R.layout.fragment_login) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickListener()
    }

    private fun clickListener() {
        val button = view?.findViewById<Button>(R.id.button)
        button?.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_appFragment
            )
        }
    }
}