package br.digitalhouse.bitdex.data.dto.cryptos

import com.google.gson.annotations.SerializedName

class Cryptos: ArrayList<Coins>()

data class Coins(
    val name: String,
    val symbol: String,
    @SerializedName("current_price")
    val price: Double,
    val image: String,
)