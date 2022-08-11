package br.digitalhouse.bitdex.ui.viewmodel

import br.digitalhouse.bitdex.data.dto.conversor.ConversorRepository
import com.google.gson.JsonObject
import retrofit2.Call
import androidx.lifecycle.ViewModel

class ConversorViewModel : ViewModel(){
    val conversorRepository = ConversorRepository()

    fun getCurrencies(): Call<JsonObject> = conversorRepository.getCurrencies()
    fun getCurrenciesRate(from: String, to: String): Call<JsonObject> = conversorRepository.getCurrenciesRate(from, to)
}