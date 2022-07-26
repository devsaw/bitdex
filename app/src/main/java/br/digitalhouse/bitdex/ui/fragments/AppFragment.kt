package br.digitalhouse.bitdex.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.digitalhouse.bitdex.R
import com.google.android.material.navigation.NavigationBarView

class AppFragment : Fragment(R.layout.fragment_app) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        replaceFrag(FavoritosFragment())
    }

    private fun initViews(view: View) {
        val bottomNav = view.findViewById<NavigationBarView>(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.favorite -> replaceFrag(FavoritosFragment())
                R.id.converter -> replaceFrag(ConversorFragment())
            }
            true
        }
    }

    private fun replaceFrag(fragment: Fragment) {
        if (fragment!=null){
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame_layout, fragment)
                transaction?.commit()
        }
    }
}