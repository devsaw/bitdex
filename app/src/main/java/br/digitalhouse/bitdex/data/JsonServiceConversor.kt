package br.digitalhouse.bitdex.data

import android.util.Log
import br.digitalhouse.bitdex.data.dto.conversor.ConversorInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL_CONV = "https://cdn.jsdelivr.net/"

private val interceptor_conv = HttpLoggingInterceptor {
    Log.d("RETROFIT_CLIENT_CONV", it)
}
    .apply { level = HttpLoggingInterceptor.Level.BODY }

private val client_conv = OkHttpClient.Builder()
    .addInterceptor(interceptor_conv)
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL_CONV)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client_conv)
    .build()

val conversorApi: ConversorInterface = retrofit.create(ConversorInterface::class.java)