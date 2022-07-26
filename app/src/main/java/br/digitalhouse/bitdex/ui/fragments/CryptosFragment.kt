package br.digitalhouse.bitdex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.ui.adapter.CryptosAdapter
import br.digitalhouse.bitdex.ui.model.Cryptos

class CryptosFragment: Fragment(R.layout.fragment_cryptos){
    private lateinit var cryptosAdapter: CryptosAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCryptosAdapter()
    }

    fun setupCryptosAdapter() {
        val listaCryptos : MutableList<Cryptos> = mutableListOf()
        cryptosAdapter = CryptosAdapter(listaCryptos)
        view?.findViewById<RecyclerView>(R.id.rcCryptos)?.adapter = cryptosAdapter
    }
}