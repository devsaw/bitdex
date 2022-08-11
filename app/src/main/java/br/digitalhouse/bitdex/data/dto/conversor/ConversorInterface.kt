package br.digitalhouse.bitdex.data.dto.conversor

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ConversorInterface {
    @GET("/gh/fawazahmed0/currency-api@1/latest/currencies.json")
    fun getCurrencies(): Call<JsonObject>

    @GET("/gh/fawazahmed0/currency-api@1/latest/currencies/{from}/{to}.json")
    fun getCurrenciesRate(
        @Path("from", encoded = true) from: String,
        @Path("to", encoded = true) to: String
    ): Call<JsonObject>
}