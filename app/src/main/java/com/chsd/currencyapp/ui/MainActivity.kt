package com.chsd.currencyapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.chsd.currencyapp.adapters.RvAdapter
import com.chsd.currencyapp.databinding.ActivityMainBinding
import com.chsd.currencyapp.db.AppDatabase
import com.chsd.currencyapp.entity.CurrencyData
import com.chsd.currencyapp.network.NetworkHelper
import com.chsd.currencyapp.services.GetData
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db: AppDatabase
    lateinit var rvAdapter: RvAdapter
    lateinit var dataList: List<CurrencyData>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.getInstance(this)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )

        window.clearFlags(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        val networkHelper = NetworkHelper(this)
        if (networkHelper.isConnected()) {
            val work = PeriodicWorkRequestBuilder<GetData>(15, TimeUnit.MINUTES)
                .build()
            WorkManager.getInstance(this).enqueue(work)
        } else {
            dataList = db.currencyDao().getDataList()
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        dataList = db.currencyDao().getDataList()
        rvAdapter = RvAdapter(dataList, this, object : RvAdapter.onPress {
            override fun onclick(currencyData: CurrencyData, position: Int) {
                var intent = Intent(this@MainActivity, ViewActivity::class.java)
                intent.putExtra("data", currencyData)
                intent.putExtra("pos", position)
                startActivity(intent)
            }
        })
        binding.rv.adapter = rvAdapter
    }
}