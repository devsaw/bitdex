package br.digitalhouse.bitdex.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.ui.adapter.FavoritosAdapter
import br.digitalhouse.bitdex.ui.model.Cryptos
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FavoritosFragment : Fragment(R.layout.fragment_favoritos) {
    private lateinit var favoritosAdapter: FavoritosAdapter
    private lateinit var floatingButton: FloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFavoritosAdapter()
        initViews(view)
        setupListener()
    }

    private fun setupFavoritosAdapter() {
        val listaFavoritos: MutableList<Cryptos> = mutableListOf()
        favoritosAdapter = FavoritosAdapter(listaFavoritos)
        view?.findViewById<RecyclerView>(R.id.rcFavorite)?.adapter = favoritosAdapter
    }

    private fun initViews(view: View) {
        floatingButton = view.findViewById(R.id.floatingButton)
        floatingButton.visibility = View.VISIBLE
    }

    private fun setupListener() {
        floatingButton.setOnClickListener {
            findNavController().navigate(R.id.cryptosFragment)
        }
    }
}