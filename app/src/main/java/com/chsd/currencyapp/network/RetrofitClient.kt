package com.chsd.currencyapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://cbu.uz/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}