package br.digitalhouse.bitdex.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.databinding.FragmentConversorBinding
import br.digitalhouse.bitdex.ui.viewmodel.ConversorViewModel
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConversorFragment : Fragment(R.layout.fragment_conversor) {
    private val conversorViewModel: ConversorViewModel by viewModels()
    private lateinit var bindingCv: FragmentConversorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingCv = FragmentConversorBinding.inflate(layoutInflater)
        return (bindingCv.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCurrencies()
        clickListener()
    }

    fun clickListener(){
        bindingCv.btResult.setOnClickListener{getCurrenciesRate()}
    }

    fun getCurrenciesRate() {
        lifecycleScope.launch {
            conversorViewModel.getCurrenciesRate(
                bindingCv.from.selectedItem.toString(),
                bindingCv.to.selectedItem.toString()
            ).enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    val dataRate = response.body()?.entrySet()?.find { it.key == bindingCv.to.selectedItem.toString() }
                    val rate : Double = dataRate?.value.toString().toDouble()
                    val conversion = bindingCv.editTxtNumber.text.toString().toDouble() * rate

                    bindingCv.textResult.setText(conversion.toString())
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.e("GET_CURRENCIES_RATE", "Erro!")
                }
            })
        }
    }

    fun getCurrencies() {
        lifecycleScope.launch {
            conversorViewModel.getCurrencies().enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    val data = mutableListOf<String>()
                    response.body()?.keySet()?.iterator()?.forEach { data.add(it) }
                    val btc = data.indexOf("btc")
                    val usd = data.indexOf("usd")
                    var adapterSpin =
                        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, data)
                    bindingCv.from.adapter = adapterSpin
                    bindingCv.to.adapter = adapterSpin

                    bindingCv.from.setSelection(btc)
                    bindingCv.to.setSelection(usd)
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.e("GET_CURRENCIES", "Erro!")
                }

            })
        }
    }
}