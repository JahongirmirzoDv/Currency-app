package com.chsd.currencyapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.chsd.currencyapp.entity.CurrencyData

@Dao
interface CurrencyDao {
    @Insert
    fun addData(currencyData: List<CurrencyData>)

    @Insert
    fun addSimpleData(currencyData: CurrencyData)

    @Query("select * from currencydata")
    fun getDataList(): List<CurrencyData>
}