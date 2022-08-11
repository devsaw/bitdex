package br.digitalhouse.bitdex.data

import android.util.Log
import br.digitalhouse.bitdex.data.dto.cryptos.CryptosInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.coingecko.com/"

private val interceptor = HttpLoggingInterceptor {
    Log.d("RETROFIT_CLIENT_CRYPT", it)
}
    .apply { level = HttpLoggingInterceptor.Level.BODY }

private val client = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

val cryptosApi: CryptosInterface = retrofit.create(CryptosInterface::class.java)