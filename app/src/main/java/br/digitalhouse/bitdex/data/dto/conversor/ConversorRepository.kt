package br.digitalhouse.bitdex.data.dto.conversor

import br.digitalhouse.bitdex.data.conversorApi
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class ConversorRepository {
    private val apiConversor = conversorApi

    fun getCurrencies(): Call<JsonObject> = apiConversor.getCurrencies()
    fun getCurrenciesRate(from: String, to: String): Call<JsonObject> = apiConversor.getCurrenciesRate(from, to)
}