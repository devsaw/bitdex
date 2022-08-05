package br.digitalhouse.bitdex.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.digitalhouse.bitdex.data.dto.Cryptos
import br.digitalhouse.bitdex.data.dto.CryptosRepository
import br.digitalhouse.bitdex.data.dto.Dados
import br.digitalhouse.bitdex.data.dto.Usd

class CryptosViewModel: ViewModel() {
    val cryptosRepository = CryptosRepository()

    suspend fun fetchCryptos(): Cryptos = cryptosRepository.fetchCryptos()
}