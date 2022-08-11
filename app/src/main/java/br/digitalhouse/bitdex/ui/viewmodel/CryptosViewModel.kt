package br.digitalhouse.bitdex.ui.viewmodel

import androidx.lifecycle.ViewModel
import br.digitalhouse.bitdex.data.dto.cryptos.Cryptos
import br.digitalhouse.bitdex.data.dto.cryptos.CryptosRepository
import retrofit2.HttpException

class CryptosViewModel: ViewModel() {
    val cryptosRepository = CryptosRepository()

    suspend fun fetchCryptos(ids: String): Cryptos? =
        try {
            cryptosRepository.fetchCryptos(ids)
        }catch (e: HttpException){
            null
        }
}