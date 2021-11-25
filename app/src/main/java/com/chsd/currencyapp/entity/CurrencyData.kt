package com.chsd.currencyapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class CurrencyData(
    @PrimaryKey val id: Int,
    val Ccy: String,
    val CcyNm_EN: String,
    val CcyNm_RU: String,
    val CcyNm_UZ: String,
    val CcyNm_UZC: String,
    val Code: String,
    val Date: String,
    val Diff: String,
    val Nominal: String,
    val Rate: String,
    var img: String? = null
):Serializable