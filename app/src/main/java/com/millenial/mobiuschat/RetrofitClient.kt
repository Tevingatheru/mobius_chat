package com.millenial.mobiuschat

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {
        @JvmStatic
        private val retrofitInstance: Retrofit = Retrofit.Builder()
            .baseUrl("https://10.0.2.2:5000/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        @JvmStatic
        fun getRetrofitInstance(): Retrofit {
            return retrofitInstance
        }
    }
}
