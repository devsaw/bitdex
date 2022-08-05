package br.digitalhouse.bitdex.data.dto

import retrofit2.http.GET
import retrofit2.http.Query

interface CryptosInterface {
        @GET("v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=${API_KEY}")
        suspend fun fetchCryptos(): Cryptos
}