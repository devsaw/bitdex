package br.digitalhouse.bitdex.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.model.ListBuilder
import br.digitalhouse.bitdex.adapter.FavoritosAdapter

class FavoritosFragment: Fragment(R.layout.fragment_favoritos){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        construir()

    }

    fun construir() {
        var listBuilder = ListBuilder()
        var recyclerView = view?.findViewById<RecyclerView>(R.id.rcFavorite)
        recyclerView?.adapter = FavoritosAdapter(requireContext(), listBuilder.listaDeTopicos)
        listBuilder.add(R.drawable.logo, "25%", "CatCoin", "1.95")
    }
}