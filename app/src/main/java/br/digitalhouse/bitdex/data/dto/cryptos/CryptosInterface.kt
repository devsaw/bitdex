package br.digitalhouse.bitdex.data.dto.cryptos

import retrofit2.http.GET
import retrofit2.http.Query

interface CryptosInterface {
            //api/v3/coins/markets?vs_currency=usd&ids=cardano&order=market_cap_desc&per_page=100&page=1&sparkline=false
        @GET("api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=250&page=1&sparkline=false")
        suspend fun fetchCryptos(
                @Query("ids") ids: String,
        ): Cryptos
}