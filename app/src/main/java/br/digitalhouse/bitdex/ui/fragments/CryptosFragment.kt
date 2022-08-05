package br.digitalhouse.bitdex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.data.dto.Cryptos
import br.digitalhouse.bitdex.ui.adapter.CryptosAdapter
import br.digitalhouse.bitdex.ui.viewmodel.CryptosViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CryptosFragment : Fragment(R.layout.fragment_cryptos) {
    private lateinit var searchView: SearchView
    private lateinit var cryptosAdapter: CryptosAdapter
    private val cryptosViewModel: CryptosViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCryptosAdapter()
        requestSearch()
    }

    fun setupCryptosAdapter() {
        searchView = requireView().findViewById(R.id.pesquisar)
        val listaCryptos: MutableList<Cryptos> = mutableListOf()
        cryptosAdapter = CryptosAdapter(listaCryptos)
        requireView().findViewById<RecyclerView>(R.id.rcCryptos)?.adapter = cryptosAdapter
    }

    private fun requestSearch() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(search: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(search: String?): Boolean {
                lifecycleScope.launch {
                    delay(700)
                    val fetchCrypto = cryptosViewModel.fetchCryptos()
                    cryptosAdapter.add(fetchCrypto)
                }
                return true
            }
        })
    }
}
