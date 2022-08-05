package br.digitalhouse.bitdex.data.dto

import br.digitalhouse.bitdex.data.CryptosApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptosRepository {
    private val api = CryptosApi

    suspend fun fetchCryptos(): Cryptos = withContext(Dispatchers.IO){
        api.fetchCryptos()
    }
}