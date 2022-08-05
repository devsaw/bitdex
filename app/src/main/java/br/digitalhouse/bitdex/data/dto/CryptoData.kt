package br.digitalhouse.bitdex.data.dto

import com.google.gson.annotations.SerializedName

data class Cryptos(
    var data: List<Dados>,
)

data class Dados(
    val name: String,
    val symbol: String,
    val quote: Quote,
)

data class Quote(
    @SerializedName("USD")
    val usd: Usd
)

data class Usd(
    val price: Float,
    @SerializedName("percent_change_24h")
    val volatility: Float,
)