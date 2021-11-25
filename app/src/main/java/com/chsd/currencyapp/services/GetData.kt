package com.chsd.currencyapp.services

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.chsd.currencyapp.db.AppDatabase
import com.chsd.currencyapp.entity.CurrencyData
import com.chsd.currencyapp.network.ApiService
import com.chsd.currencyapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetData(var context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    private val TAG = "GetData"
    override fun doWork(): Result {
        loadData(context)
        return Result.success()
    }


    private fun loadData(context: Context) {
        var db = AppDatabase.getInstance(context)
        RetrofitClient.getClient().create(ApiService::class.java)
            .getListData().enqueue(object : Callback<List<CurrencyData>> {
                override fun onResponse(
                    call: Call<List<CurrencyData>>,
                    response: Response<List<CurrencyData>>
                ) {
                    if (response.isSuccessful) {
                        var body = response.body()
                        var imageList = arrayListOf(
                            "us",
                            "eu",
                            "ru",
                            "gb-eng",
                            "jp",
                            "az",
                            "bd",
                            "bg",
                            "bh",
                            "bn",
                            "br",
                            "by",
                            "ca",
                            "ch",
                            "cn",
                            "cu",
                            "cz",
                            "dm",
                            "dz",
                            "eg",
                            "af",
                            "ar",
                            "ge",
                            "hk",
                            "hu",
                            "id",
                            "il",
                            "in",
                            "iq",
                            "ir",
                            "is",
                            "jo",
                            "au",
                            "kg",
                            "kh",
                            "kr",
                            "kw",
                            "kz",
                            "la",
                            "lb",
                            "ly",
                            "ma",
                            "md",
                            "mm",
                            "mn",
                            "mx",
                            "my",
                            "no",
                            "nz",
                            "om",
                            "ph",
                            "pk",
                            "pl",
                            "qa",
                            "ro",
                            "rs",
                            "am",
                            "sa",
                            "sd",
                            "se",
                            "sg",
                            "sy",
                            "th",
                            "tj",
                            "tm",
                            "tn",
                            "tr",
                            "ua",
                            "ae",
                            "uy",
                            "ve",
                            "vn",
                            "xd",
                            "ye",
                            "za",
                            "uz"
                        )
                        if (body != null) {
                            for (i in body.indices) {
                                body[i].img = imageList[i]
                                if (db.currencyDao().getDataList().isEmpty()) {
                                    db.currencyDao().addData(body)
                                } else {
                                    db.clearAllTables()
                                    db.currencyDao().addData(body)
                                }
                            }
                        }
                        val currencyData = CurrencyData(
                            345,
                            "UZS",
                            "UZB sum",
                            "uz som",
                            "uzbek somi",
                            "1",
                            "446",
                            "24.11.2021",
                            "salom",
                            "1",
                            "1",
                            "uz"
                        )
                        db.currencyDao().addSimpleData(currencyData)
                    }
                }

                override fun onFailure(call: Call<List<CurrencyData>>, t: Throwable) {
                    t.printStackTrace()
                }

            })
    }
}