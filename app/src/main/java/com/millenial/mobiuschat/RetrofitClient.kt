package com.millenial.mobiuschat

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {
        private val sslSocketFactory = CustomSSLSocketFactory.create()
        private val trustManagerFactory = CustomTrustManager.initialize()
        val client = OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustManagerFactory)
            .hostnameVerifier { _, _ -> true }
            .build()

        @JvmStatic
        private val retrofitInstance: Retrofit = Retrofit.Builder()
            .baseUrl("https://10.0.2.2:5000/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        @JvmStatic
        fun getRetrofitInstance(): Retrofit {
            return retrofitInstance
        }
    }
}
