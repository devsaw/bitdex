package br.digitalhouse.bitdex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.adapter.CryptosAdapter
import br.digitalhouse.bitdex.adapter.FavoritosAdapter
import br.digitalhouse.bitdex.model.ListBuilder

class CryptosFragment: Fragment(R.layout.fragment_cryptos){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        construir()
    }

    fun construir() {
        var listBuilder = ListBuilder()
        var recyclerView = view?.findViewById<RecyclerView>(R.id.rcCryptos)
        recyclerView?.adapter = CryptosAdapter(requireContext(), listBuilder.listaDeTopicos)
        listBuilder.add(R.drawable.logo, "25%", "CatCoin", "1.95")
    }
}