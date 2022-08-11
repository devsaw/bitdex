package br.digitalhouse.bitdex.data.dto.cryptos

import br.digitalhouse.bitdex.data.cryptosApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class CryptosRepository {
    private val api = cryptosApi

    suspend fun fetchCryptos(ids: String): Cryptos? = withContext(Dispatchers.IO){
        try {
            api.fetchCryptos(ids)
        }catch (e: HttpException){
            null
        }

    }
}