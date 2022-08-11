package br.digitalhouse.bitdex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.data.dto.cryptos.Coins
import br.digitalhouse.bitdex.data.dto.cryptos.Cryptos
import br.digitalhouse.bitdex.ui.adapter.CryptosAdapter
import br.digitalhouse.bitdex.ui.interfaces.ToastInterface
import br.digitalhouse.bitdex.ui.viewmodel.CryptosViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CryptosFragment : Fragment(R.layout.fragment_cryptos), ToastInterface {
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
        val listaCryptos: MutableList<Coins> = mutableListOf()
        cryptosAdapter = CryptosAdapter(listaCryptos)
        requireView().findViewById<RecyclerView>(R.id.rcCryptos)?.adapter = cryptosAdapter
    }

    private fun requestSearch() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            private var searchJob: Job? = null
            override fun onQueryTextSubmit(ids: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(ids: String?): Boolean {
                searchJob?.cancel()
                searchJob = if (!ids.isNullOrBlank() && ids >= 3.toString()) {
                        lifecycleScope.launch {
                            val fetchCrypto = cryptosViewModel.fetchCryptos(ids)
                            if (fetchCrypto != null) {
                                cryptosAdapter.add(fetchCrypto)
                            }else{
                                toast("Server Overloaded")
                            }
                        }
                } else {
                    null
                }
                return true
            }
        })
    }

    override fun toast(toast: String) {
        Toast.makeText(requireContext(), toast, Toast.LENGTH_SHORT).show()
    }
}
