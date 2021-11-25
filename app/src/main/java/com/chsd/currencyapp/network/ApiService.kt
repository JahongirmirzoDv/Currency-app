package com.chsd.currencyapp.network

import com.chsd.currencyapp.entity.CurrencyData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET("uzc/arkhiv-kursov-valyut/json/")
    fun getListData(): Call<List<CurrencyData>>
}